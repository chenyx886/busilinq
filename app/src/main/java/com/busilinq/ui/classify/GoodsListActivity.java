package com.busilinq.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.classify.IGoodsListView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.classify.GoodsListPresenter;
import com.busilinq.ui.classify.adapter.GoodsAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：分类商品列表
 * Create Person：Chenyx
 * Create Time：2018/1/31 下午3:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsListActivity extends BaseMvpActivity<GoodsListPresenter> implements IGoodsListView {

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;
    /**
     * 搜索框
     */
    @BindView(R.id.et_search)
    TextView mEtSearch;
    /**
     * 搜索图标
     */
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
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
    /**
     * 判断排序状态
     */
    private int clickStatus = 0;
    private String field;//排序类型
    private String sort;//升降

    private GoodsAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    /**
     * 分类id
     */
    private String classifyId;
    /**
     * 名称
     */
    private String cateName;

    @Override
    protected GoodsListPresenter createPresenter() {
        return new GoodsListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_goods_list);
    }


    @Override
    protected void initUI() {
        classifyId = getIntent().getStringExtra("classifyId");
        cateName = getIntent().getStringExtra("cateName");
        mEtSearch.setText(cateName + "");

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setArrowImageView(R.mipmap.iconfont_downgrey);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallPulse);

        mAdapter = new GoodsAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoodsId());
                JumpUtil.overlay(GoodsListActivity.this, GoodsDetailActivity.class, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                mPresenter.getGoodsList(UserCache.GetUserId(), classifyId, page, field, sort);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getGoodsList(UserCache.GetUserId(), classifyId, page, field, sort);
            }
        });
        mDataList.setRefreshing(true);
    }


    /**
     * 商品列表
     *
     * @param list
     */
    @Override
    public void GoodsList(PageEntity<HomeGoodsEntity> list) {
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

    @OnClick({R.id.tv_back, R.id.et_search, R.id.iv_search, R.id.tv_popularity, R.id.tv_time, R.id.tv_price})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("classifyId", classifyId);
        bundle.putString("cateName", cateName);

        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.et_search:
                JumpUtil.overlay(this, GoodSearchActivity.class, bundle);
                break;
            case R.id.iv_search:
                JumpUtil.overlay(this, GoodSearchActivity.class, bundle);
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