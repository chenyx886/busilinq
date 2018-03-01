package com.busilinq.ui.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.classify.ISpecialGoodsListView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.SpecialGoodsEntity;
import com.busilinq.presenter.classify.SpecialGoodsListPresenter;
import com.busilinq.ui.classify.adapter.SpecialGoodsAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：特价商品列表
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午3:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SpecialGoodsListActivity extends BaseMvpActivity<SpecialGoodsListPresenter> implements ISpecialGoodsListView {

    public static final int HOME_REQUESTCODE = 1;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;
    /**
     * 人气
     */
    @BindView(R.id.tv_popularity)
    TextView mPopularity;
    /**
     * 时间
     */
    @BindView(R.id.tv_time)
    TextView mTime;
    /**
     * 价格
     */
    @BindView(R.id.tv_price)
    TextView mPrice;

    private SpecialGoodsAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    /**
     * 分类id
     */
    private int classifyId;
    /**
     * 排序方式ASC DESC逆序
     */
    private String sort = "DESC";
    /**
     * 排序字段按人气(销量):salesVolume,按价格:price,按时间:update_time
     */
    private String field;

    @Override
    protected SpecialGoodsListPresenter createPresenter() {
        return new SpecialGoodsListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_special_goods_list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoodsDetailActivity.HOME_REQUESTCODE && resultCode == 10) {
            setResult(10);
            finish();
        }
    }

    @Override
    protected void initUI() {
        classifyId = getIntent().getIntExtra("classifyId", 0);
        mTitle.setText("特价商品");
        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setArrowImageView(R.mipmap.iconfont_downgrey);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallPulse);

        mAdapter = new SpecialGoodsAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoods().getGoodsId());
                JumpUtil.startForResult(SpecialGoodsListActivity.this, GoodsDetailActivity.class, GoodsDetailActivity.HOME_REQUESTCODE, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                mPresenter.getSpecialGoodsList(UserCache.GetUserId(), classifyId, page, sort, field);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getSpecialGoodsList(UserCache.GetUserId(), classifyId, page, sort, field);
            }
        });
        mDataList.setRefreshing(true);
    }


    /**
     * 显示特价商品
     *
     * @param list
     */
    @Override
    public void ShowGoodsList(PageEntity<SpecialGoodsEntity> list) {
        if (state == STATE_PULL_REFRESH) {
            page = 1;
            mAdapter.setData(list.getList());
        } else if (state == STATE_LOAD_MORE && list.getLimit() > 0) {
            mAdapter.insert(mAdapter.getItemCount(), list.getList());
        } else {
            if (mDataList != null)
                mDataList.setNoMore(true);
        }
        ++page;
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {
        if (state == STATE_PULL_REFRESH) {
            if (mDataList != null)
                mDataList.refreshComplete();
        } else if (state == STATE_LOAD_MORE) {
            if (mDataList != null)
                mDataList.loadMoreComplete();
        }
        if (mAdapter.getItemCount() - 1 > 0) {
            mDataList.setLoadingMoreEnabled(true);
        } else {
            mDataList.setLoadingMoreEnabled(false);
        }
    }

    @OnClick({R.id.tv_back, R.id.tv_popularity, R.id.tv_time, R.id.tv_price})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            //人气
            case R.id.tv_popularity:
                field = "salesVolume";
                sort = mPopularity.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mPopularity.setTag("DESC");
                else
                    mPopularity.setTag("ASC");
                break;
            //时间
            case R.id.tv_time:
                field = "update_time";
                sort = mTime.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mTime.setTag("DESC");
                else
                    mTime.setTag("ASC");
                break;
            //价格
            case R.id.tv_price:
                field = "price";
                sort = mPrice.getTag().toString();
                mDataList.setRefreshing(true);
                if (sort.equals("ASC"))
                    mPrice.setTag("DESC");
                else
                    mPrice.setTag("ASC");
                break;
        }
    }


}