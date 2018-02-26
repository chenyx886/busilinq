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
import butterknife.ButterKnife;
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
     * 名称
     */
    private String cateName;

    @Override
    protected SpecialGoodsListPresenter createPresenter() {
        return new SpecialGoodsListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_special_goods_list);
        ButterKnife.bind(this);
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
        cateName = getIntent().getStringExtra("cateName");

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallScaleMultiple);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

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
                mPresenter.getSpecialGoodsList(UserCache.GetUserId(), classifyId, page);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getSpecialGoodsList(UserCache.GetUserId(), classifyId, page);
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
        } else if (state == STATE_LOAD_MORE) {
            mAdapter.insert(mAdapter.getItemCount(), list.getList());
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
    }

    @OnClick({R.id.tv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }


}