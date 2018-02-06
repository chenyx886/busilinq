package com.busilinq.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.MyCollectionView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.MyCollectionEntity;
import com.busilinq.presenter.mine.MyCollectionPresenter;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.busilinq.ui.mine.adapter.MyCollectionAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：我的收藏
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午4:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MyCollectionActivity extends BaseMvpActivity<MyCollectionPresenter> implements MyCollectionView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 筛选
     */
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    /**
     * 数据列表
     */
    @BindView(R.id.xr_data_list)
    XRecyclerView mDataList;

    private MyCollectionAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected MyCollectionPresenter createPresenter() {
        return new MyCollectionPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText(R.string.my_collection);
        tv_confirm.setVisibility(View.VISIBLE);
        tv_confirm.setText("筛选");

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallScaleMultiple);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);

        mAdapter = new MyCollectionAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("goodsId", mAdapter.getItem(position).getGoods().getGoods().getGoodsId());
                JumpUtil.overlay(mContext, GoodsDetailActivity.class, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                mPresenter.getMyCollectionList(UserCache.GetUserId(), page);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getMyCollectionList(UserCache.GetUserId(), page);
            }
        });
        mDataList.setRefreshing(true);
    }


    @OnClick({R.id.tv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;

        }
    }

    @Override
    public void showProgress(String message) {
//        MLoadingDialog.show(this, message);
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

    @Override
    public void getMyCollectionList(PageEntity<MyCollectionEntity> list) {
        if (state == STATE_PULL_REFRESH) {
            page = 1;
            mAdapter.setData(list.getList());
        } else if (state == STATE_LOAD_MORE) {
            mAdapter.insert(mAdapter.getItemCount(), list.getList());
        }
        ++page;
    }
}