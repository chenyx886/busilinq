package com.busilinq.xsm.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.widget.MListView;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.presenter.XsmOrdersPresenter;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;
import com.busilinq.xsm.ulits.StringParse;
import com.busilinq.xsm.viewinterface.IXsmOrdersView;
import com.busilinq.xsm.widget.CommonAlertDialog;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnAlertDialogListener;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2017/6/6.
 */

public class XsmOrdersActivity extends XsmBaseActivity implements IXsmOrdersView {

    @BindView(R.id.myxsm_ordering_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_ordering_error_layout)
    EmptyLayout mErrorLayout;
    @BindView(R.id.myxsm_ordering_container)
    LinearLayout mContainer;
    @BindView(R.id.xms_current_order_btn_lly)
    LinearLayout mCurrentOrderBtnLly;
    @BindView(R.id.myxsm_no_order_lly)
    LinearLayout mNoOrderLly;
    @BindView(R.id.myxsm_current_order_lly)
    LinearLayout mCurrentOrderLly;
    @BindView(R.id.xms_current_order_pay_seach_btn_lly)
    LinearLayout mOrderPaySeachBtnLly;

    @BindView(R.id.myxsm_order_detail_lv)
    MListView mCurrentOrderDetailLv;
    @BindView(R.id.xsm_current_order_number_tv)
    TextView mCurrentOderNumberTv;
    @BindView(R.id.xsm_current_order_time_tv)
    TextView mCurrentOderTimeTv;
    @BindView(R.id.xsm_current_order_type_tv)
    TextView mCurrentOderTypeTv;

    @BindView(R.id.xsm_current_order_total_number_tv)
    TextView mCurrentOderTotalNumberTv;
    @BindView(R.id.xsm_current_order_total_tv)
    TextView mCurrentOderTotalTv;

    @BindView(R.id.xsm_pay_btn)
    Button payBtn;

    private CommonAlertDialog mDelAlertDialog;

    private XsmOrdersPresenter ordersPresenter;

    @Override
    public int initContentView() {
        return R.layout.myxsm_order_activity;
    }

    @Override
    public void initData() {
        //初始化订单列表业务逻辑：P层
        ordersPresenter = new XsmOrdersPresenter();
        ordersPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        mHeadbar.setMidMsg(ordersPresenter.getMerchant().getCustName());
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        payBtn.setEnabled(true);
        ordersPresenter.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        payBtn.setEnabled(true);
        ordersPresenter.start();
    }

    @Override
    protected void onDestroy() {
        ordersPresenter.cancel();
        super.onDestroy();
    }

    public void showLoading(int visibility) {
        if (visibility == View.VISIBLE) {
            mContainer.setVisibility(View.GONE);
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        } else {
            mContainer.setVisibility(View.VISIBLE);
            mErrorLayout.dismiss();
        }
    }

    @Override
    public void showLoadingError(int errorType) {
        mErrorLayout.setErrorType(errorType);
    }


    @OnClick({R.id.xsm_current_order_pay_seach_btn, R.id.xsm_history_orders_lly,
            R.id.xsm_current_order_up_btn, R.id.xsm_current_order_del_btn,
            R.id.xsm_pay_btn, R.id.myxsm_order_now_btn})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.xsm_pay_order_cancel_btn:
//                ordersPresenter.unLockedOrder();
//                break;
            case R.id.myxsm_order_now_btn:
                showActivity(this, XsmOrderingActivity.class);
                break;
            case R.id.xsm_pay_btn:
//                付款功能
                if (payBtn.isEnabled()) {
                    ordersPresenter.payment();
                }
                break;
            case R.id.xsm_current_order_del_btn:
                if (mDelAlertDialog == null) {
                    mDelAlertDialog = new CommonAlertDialog(this);
                    mDelAlertDialog.setDialogListener(mOnClearDialogListener);
                    mDelAlertDialog.setTitle(getResources().getString(R.string.myxsm_delete_order_title));
                    mDelAlertDialog.setSureMsg(getResources().getString(R.string.myxsm_delete));
                }
                mDelAlertDialog.setMsg(getResources().getString(R.string.myxsm_delete_order_msg));
                mDelAlertDialog.show();
                break;
            case R.id.xsm_current_order_up_btn:
                Bundle bundle = new Bundle();
                bundle.putSerializable(Order.class.getSimpleName(), ordersPresenter.getCurrentOrder());
                showActivity(this, XsmOrderingActivity.class, bundle);
                break;
            case R.id.xsm_history_orders_lly:
                ordersPresenter.showHistoryOrders();
                break;
            case R.id.xsm_current_order_pay_seach_btn:
                //改为查询订单状态，不在是继续支付（动作：通知后台查询订单是否支付并解锁订单）
                ordersPresenter.queryPayOrder();
                break;
        }
    }

    @Override
    public void updateCurrentOrder(Order order, int number, double total) {
        if (order == null) {
            mNoOrderLly.setVisibility(View.VISIBLE);
            mCurrentOrderLly.setVisibility(View.GONE);
        } else {
            mNoOrderLly.setVisibility(View.GONE);
            mCurrentOrderLly.setVisibility(View.VISIBLE);
            mCurrentOderNumberTv.setText(order.getCoNum());
            if (order.getPmtStatus().equals("0")) {
                mCurrentOderTypeTv.setTextColor(getResources().getColor(R.color.color_f20c0c));
                mCurrentOrderBtnLly.setVisibility(View.VISIBLE);
                mOrderPaySeachBtnLly.setVisibility(View.GONE);
            } else {
                if (order.getPmtStatus().equals("1")) {
                    mCurrentOderTypeTv.setTextColor(getResources().getColor(R.color.color_009999));
                    mOrderPaySeachBtnLly.setVisibility(View.GONE);
                } else {
                    mCurrentOderTypeTv.setTextColor(getResources().getColor(R.color.color_f20c0c));
                    mOrderPaySeachBtnLly.setVisibility(View.VISIBLE);
                }
                mCurrentOrderBtnLly.setVisibility(View.GONE);

            }
            mCurrentOderTimeTv.setText(StringParse.getDate(mContext, order.getOrderDate(), "."));
            mCurrentOderTypeTv.setText(StringParse.getOrderPayStatus(mContext, order.getPmtStatus()));
            mCurrentOderTotalNumberTv.setText(getResources().getString(R.string.gong) + number + getResources().getString(R.string.tiao));
            mCurrentOderTotalTv.setText(getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(total));
        }
    }

    @Override
    public void updateList(XsmOrderDetailAdapter adapter) {
        mCurrentOrderDetailLv.setAdapter(adapter);
        setListViewHeightBasedOnChildren(mCurrentOrderDetailLv);
    }

    /**
     * 计算listView高度，解决ScrollView嵌套listview引起的高度显示不全问题
     *
     * @param listView
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private OnAlertDialogListener mOnClearDialogListener = new OnAlertDialogListener() {
        @Override
        public void onSure() {
            super.onSure();
            mDelAlertDialog.dismiss();
            ordersPresenter.deleteOrder();
        }

        @Override
        public void onCancel() {
            super.onCancel();
            mDelAlertDialog.dismiss();
        }
    };
    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            XsmOrdersActivity.this.onBackPressed();
        }

        @Override
        public void onMid() {
            super.onMid();
        }

        @Override
        public void onRight() {
            super.onRight();
//            showActivity(XsmOrdersActivity.this, UmsPosManagerActivity.class);
        }
    };
}
