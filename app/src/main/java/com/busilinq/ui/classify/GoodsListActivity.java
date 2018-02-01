package com.busilinq.ui.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.classify.IGoodsListView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.GoodsEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.classify.GoodsListPresenter;
import com.busilinq.ui.classify.adapter.GoodsAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;
    /**
     * 搜索框
     */
    @BindView(R.id.et_search)
    EditText mEtSearch;
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

    private GoodsAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    private int cateId;
    private String cateName;

    @Override
    protected GoodsListPresenter createPresenter() {
        return new GoodsListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        mBack.setVisibility(View.VISIBLE);
        cateId = getIntent().getIntExtra("cateId", 0);
        cateName = getIntent().getStringExtra("cateName");
        mEtSearch.setText(cateName + "");

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallScaleMultiple);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

        mAdapter = new GoodsAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoodsId());
                JumpUtil.overlay(mContext, GoodsDetailActivity.class, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                state = STATE_PULL_REFRESH;
                mPresenter.getGoodsList("", cateId, page);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getGoodsList("", cateId, page);
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