package com.busilinq.ui.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.order.IMyOrdersView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.data.event.MenuEvent;
import com.busilinq.presenter.mine.order.MyOrderPresenter;
import com.busilinq.ui.mine.order.adapter.MyOrdersAdapter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的订单（我订过的，退货单）
 * Created by yu on 2018/1/31.
 */

public class MyOrdersActivity extends BaseMvpActivity<MyOrderPresenter> implements IMyOrdersView {
    public static final String WAIT_PAY = "WAIT_PAY";//待审核
    public static final String WAIT_OUT = "WAIT_OUT";//待出库
    public static final String WAIT_SEND = "WAIT_SEND";//待发货
    public static final String WAIT_RECEIVE = "WAIT_RECEIVE";//待接收
    public static final String COMPLETE = "COMPLETE";//完成
    public static final String REFUND = "REFUND";//退款
    public static final String UNUSUAL = "UNUSUAL";//异常
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 数据列表
     */
    @BindView(R.id.my_orders_data_list)
    XRecyclerView mDataList;
    /**
     * 订单适配器
     */
    private MyOrdersAdapter mAdapter;

    private static int state = -1;
    private static int STATE_LOAD_MORE = 0X10;
    private static int STATE_PULL_REFRESH = 0X20;
    private int page = 1;

    private String seachType;

    private boolean isGoClassify = false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_orders);
    }

    @Override
    protected MyOrderPresenter createPresenter() {
        return new MyOrderPresenter(this);
    }

    @Override
    protected void initUI() {
        String tilteValue = getIntent().getStringExtra(MyOrdersActivity.class.getSimpleName());
        mTitle.setText(R.string.my_orders_title);
        if (tilteValue != null && tilteValue.equals(COMPLETE)) {
            seachType = COMPLETE;
            mTitle.setText("我订过的");
        } else if (tilteValue != null && tilteValue.equals(WAIT_SEND)) {
            //代发货就是已付款的单子
            seachType = WAIT_SEND;
            mTitle.setText("付款单");
        } else if (tilteValue != null && tilteValue.equals(WAIT_RECEIVE)) {
            //代接收就是发货单
            seachType = WAIT_RECEIVE;
            mTitle.setText("发货单");
        } else if (tilteValue != null && tilteValue.equals(REFUND)) {
            //退货单
            seachType = REFUND;
            mTitle.setText("退货单");
        }

        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setNoMore(true);
        mDataList.setLoadingMoreEnabled(false);
        mDataList.setArrowImageView(R.mipmap.iconfont_downgrey);
        mDataList.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        mDataList.setRefreshProgressStyle(ProgressStyle.BallPulse);

        mAdapter = new MyOrdersAdapter(this);
        mDataList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AbstractRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                isGoClassify = true;
                MApplication.getInstance().appManager.finishAllActivity();
            }
        });
        mAdapter.setOnItemViewClickListener(new AbstractRecyclerViewAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                Bundle bundle = new Bundle();
                String orderNum = mAdapter.getItem(position).getOrder().getOrderNumber();
                bundle.putString("orderNum", orderNum);
                JumpUtil.startForResult(MyOrdersActivity.this, MyOrdersDetailActivity.class, 1, bundle);
            }
        });

        mDataList.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                page = 1;
                state = STATE_PULL_REFRESH;
                mPresenter.getOrdersList(UserCache.GetUserId(), page, seachType);
            }

            @Override
            public void onLoadMore() {
                state = STATE_LOAD_MORE;
                mPresenter.getOrdersList(UserCache.GetUserId(), page, seachType);
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
    public void OrdersList(PageEntity<HomeOrderEntity> list) {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            mDataList.setRefreshing(true);
        }
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isGoClassify) {
            EventBus.getDefault().post(new MenuEvent(2));
        }
    }
}
