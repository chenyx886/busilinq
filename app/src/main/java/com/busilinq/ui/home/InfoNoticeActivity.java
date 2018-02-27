package com.busilinq.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.home.IInfoNoticeView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.InfoNoticeEntity;
import com.busilinq.presenter.home.InfoNoticePresenter;
import com.busilinq.ui.home.adapter.InfoNoticeAdapter;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：信息公告
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class InfoNoticeActivity extends BaseMvpActivity<InfoNoticePresenter> implements IInfoNoticeView {

    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    /**
     * 数据列表
     */
    @BindView(R.id.info_notice_list)
    XRecyclerView mDataList;

    private InfoNoticeAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    public int page = 1;

    @Override
    protected InfoNoticePresenter createPresenter() {
        return new InfoNoticePresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_info_notice);
    }

    @Override
    protected void initUI() {
        mBack.setVisibility(View.VISIBLE);
        mTitle.setText("信息公告");
        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallScaleMultiple);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mAdapter = new InfoNoticeAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("url", mAdapter.getItem(position).getUrl());
                JumpUtil.overlay(InfoNoticeActivity.this,InfoNoticeDetailActivity.class,bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                mPresenter.getInfoNoticeList(UserCache.GetUserId(), page);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getInfoNoticeList(UserCache.GetUserId(), page);
            }
        });
        mDataList.setRefreshing(true);
    }

    @Override
    public void showNoticeList(PageEntity<InfoNoticeEntity> list) {
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