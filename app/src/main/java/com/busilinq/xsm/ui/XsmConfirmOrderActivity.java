package com.busilinq.xsm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.widget.MListView;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.presenter.XsmConfirmOrderPresenter;
import com.busilinq.xsm.ui.adapter.XsmOrderingAdapter;
import com.busilinq.xsm.ulits.StringParse;
import com.busilinq.xsm.ulits.StringUtils;
import com.busilinq.xsm.viewinterface.IXsmConfirmOrderView;
import com.busilinq.xsm.widget.CommonAlertDialog;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnAlertDialogListener;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2017/6/13.
 * 确认订单接界面
 */

public class XsmConfirmOrderActivity extends XsmBaseActivity implements IXsmConfirmOrderView {

    @BindView(R.id.myxsm_ordering_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_ordering_error_layout)
    EmptyLayout mErrorLayout;
    @BindView(R.id.myxsm_ordering_lv)
    MListView mOrderingLv;
    @BindView(R.id.xms_ordering_cgt_tv)
    TextView mCgyTv;
    @BindView(R.id.xms_ordering_sum_tv)
    TextView mQtyTv;
    @BindView(R.id.xsm_ordering_total_tv)
    TextView mTotalTv;
    @BindView(R.id.xms_ordering_remain_tv)
    TextView mRemainTv;
    @BindView(R.id.xms_ordering_category_tv)
    TextView mCategoryTv;
    @BindView(R.id.xsm_accout_name_tv)
    TextView mNameTv;
    @BindView(R.id.xsm_accout_tv)
    TextView mAccoutTv;
    @BindView(R.id.confirm_date_time_tv)
    TextView mDateTimeTv;
    @BindView(R.id.myxsm_confirm_slv)
    ScrollView mConfirmSlv;

    private XsmConfirmOrderPresenter mPresenter;
    private CommonAlertDialog mPushOrderAlertDialog;

    @Override
    public int initContentView() {
        return R.layout.myxsm_confirm_order_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmConfirmOrderPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        mErrorLayout.dismiss();
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mNameTv.setText(mPresenter.getMerchant().getCustName());
        mAccoutTv.setText(mPresenter.getMerchant().getCustCode());
        mDateTimeTv.setText(StringUtils.getCurrentTimeStr());
        mHeadbar.setMidMsg("确认订货信息");
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        mPresenter.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        mPresenter.cancel();
        super.onDestroy();
    }

    public void showLoading(int visibility) {
        if (visibility == View.VISIBLE) {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        } else {
            mErrorLayout.dismiss();
        }
    }

    @Override
    public void showLoadingError(int errorType) {
        mErrorLayout.setErrorType(errorType);
    }


    @Override
    public void updateRemain(int remain) {
        String msg = mContext.getResources().getString(R.string.myxsm_can_order);
        msg += remain + mContext.getResources().getString(R.string.tiao);
        mRemainTv.setText(msg);
    }

    @Override
    public void updateCategory(int category) {
        String msg = category + mContext.getResources().getString(R.string.unit_category);
        mCategoryTv.setText(msg);
    }

    @Override
    public void updateReqSum(int special, int normal) {
        String unit = mContext.getResources().getString(R.string.tiao);
        mQtyTv.setText(mContext.getResources().getString(R.string.myxsm_order_total) + String.valueOf(special + normal) + unit);
        String msg = mContext.getResources().getString(R.string.myxsm_ordering_cgt_normal) + " "
                + normal + " " + unit + " " + "  "
                + mContext.getResources().getString(R.string.myxsm_ordering_cgt_special) + special + " "
                + unit;
        mCgyTv.setText(msg);
    }

    @Override
    public void updateAmount(String amount) {
        String amt = getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(amount);
        mTotalTv.setText(amt);
    }


    @Override
    public void updateListView(XsmOrderingAdapter adapter) {
        mOrderingLv.setAdapter(adapter);
        setListViewHeightBasedOnChildren(mOrderingLv);
        mConfirmSlv.fullScroll(ScrollView.FOCUS_UP);
    }

    @OnClick({R.id.myxsm_orders_commit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myxsm_orders_commit_btn:
                if (mPushOrderAlertDialog == null) {
                    mPushOrderAlertDialog = new CommonAlertDialog(this);
                    mPushOrderAlertDialog.setDialogListener(mOnPushDialogListener);
                    mPushOrderAlertDialog.setTitle(getResources().getString(R.string.myxsm_ordering_commit));
                    mPushOrderAlertDialog.setSureMsg(getResources().getString(R.string.order_status_add));
                }
                mPushOrderAlertDialog.setMsg(getResources().getString(R.string.myxsm_ordering_commit_msg));
                mPushOrderAlertDialog.show();
                break;
        }
    }

    private OnAlertDialogListener mOnPushDialogListener = new OnAlertDialogListener() {
        @Override
        public void onSure() {
            super.onSure();
            mPushOrderAlertDialog.dismiss();
            mPresenter.addOrder();
        }

        @Override
        public void onCancel() {
            super.onCancel();
            mPushOrderAlertDialog.dismiss();
        }
    };
    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            XsmConfirmOrderActivity.this.onBackPressed();
        }

    };

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
}
