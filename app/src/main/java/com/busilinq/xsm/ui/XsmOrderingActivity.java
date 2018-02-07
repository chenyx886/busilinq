package com.busilinq.xsm.ui;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.presenter.XsmOrderingPresenter;
import com.busilinq.xsm.ui.adapter.XsmOrderingAdapter;
import com.busilinq.xsm.ulits.StringParse;
import com.busilinq.xsm.viewinterface.IXsmOrderingView;
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

public class XsmOrderingActivity extends XsmBaseActivity implements IXsmOrderingView {

    @BindView(R.id.myxsm_ordering_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_ordering_error_layout)
    EmptyLayout mErrorLayout;
    @BindView(R.id.xsm_shops_lv)
    ListView mOrdersLv;
    @BindView(R.id.myxsm_ordering_lv)
    ListView mOrderingLv;
    @BindView(R.id.bottom_lly)
    LinearLayout mBottomLly;
    @BindView(R.id.xsm_shop_cart_rly)
    RelativeLayout mShopCartRly;
    @BindView(R.id.xms_ordering_remain_tv)
    TextView mRemainTv;
    @BindView(R.id.xms_ordering_cgt_tv)
    TextView mCgyTv;
    @BindView(R.id.xms_ordering_sum_tv)
    TextView mQtyTv;
    @BindView(R.id.xsm_ordering_total_tv)
    TextView mTotalTv;
    @BindView(R.id.xsm_ordering_shop_cart_fly)
    FrameLayout mShopCartFly;

    private XsmOrderingPresenter mPresenter;

    private CommonAlertDialog mClearAlertDialog;

    @Override
    public int initContentView() {
        return R.layout.myxsm_ordering_activity;
    }

    private static XsmOrderingActivity instance;

    /**
     * 单一实例
     */
    public static XsmOrderingActivity getYuXsmOrderingActivity() {
        return instance;
    }

    @Override
    public void initData() {
        mPresenter = new XsmOrderingPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        instance = this;
        mShopCartRly.setVisibility(View.GONE);
        mShopCartFly.setVisibility(View.GONE);
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setMidMsg(mPresenter.getMerchant().getCustName());
        mHeadbar.setRigthIgv(R.mipmap.ic_xsm_seach);
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        mPresenter.start();
    }

    public void showLoading(int visibility) {
        if (visibility == View.VISIBLE) {
            mOrderingLv.setVisibility(View.GONE);
            mShopCartRly.setVisibility(View.GONE);
            mBottomLly.setVisibility(View.GONE);
            mShopCartFly.setVisibility(View.GONE);
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        } else {
            mOrderingLv.setVisibility(View.VISIBLE);
            mBottomLly.setVisibility(View.VISIBLE);
            mShopCartFly.setVisibility(View.VISIBLE);
            mErrorLayout.dismiss();
        }
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

    @Override
    public void showLoadingError(int errorType) {
        if (errorType == EmptyLayout.NODATA) {
            mOrderingLv.setVisibility(View.VISIBLE);
            mBottomLly.setVisibility(View.VISIBLE);
        }
        mErrorLayout.setErrorType(errorType);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mPresenter.setFindedCgtInfo((Cigarette) data.getSerializableExtra(Cigarette.class.getSimpleName()));
        }
    }

    @Override
    public void updateRemain(int remain) {
        String msg = mContext.getResources().getString(R.string.myxsm_can_order);
        msg += remain + mContext.getResources().getString(R.string.tiao);
        mRemainTv.setText(msg);
    }

    @Override
    public void updateAmount(String amount) {
        String amt = getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(amount);
        mTotalTv.setText(amt);
    }

    @Override
    public void updateCartListView(XsmOrderingAdapter mCartAdapter) {
        mOrdersLv.setAdapter(mCartAdapter);
    }


    @Override
    public void updateReqSum(int special, int normal) {
        mQtyTv.setText(String.valueOf(special + normal));
        String unit = mContext.getResources().getString(R.string.tiao);
        String msg = mContext.getResources().getString(R.string.myxsm_ordering_cgt_normal) + " "
                + normal + " " + unit + " " + "  "
                + mContext.getResources().getString(R.string.myxsm_ordering_cgt_special) + special + " "
                + unit;
        mCgyTv.setText(msg);

    }


    @Override
    public void updateListView(XsmOrderingAdapter adapter) {
        mOrderingLv.setAdapter(adapter);
    }


    @OnClick({R.id.myxsm_orderlist_commit_btn, R.id.xsm_ordering_shop_cart_fly, R.id.xsm_orders_clear_lly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myxsm_orderlist_commit_btn:
                if (mPresenter.getCartsSize() > 0)
                    showActivity(this, XsmConfirmOrderActivity.class);
                else
                    toast("您没有选择任何烟品");
                break;
            case R.id.xsm_ordering_shop_cart_fly:
                if (mShopCartRly.getVisibility() == View.GONE) {
                    mShopCartRly.setVisibility(View.VISIBLE);
                } else {
                    mShopCartRly.setVisibility(View.GONE);
                }
                break;
            case R.id.xsm_orders_clear_lly:
                if (mClearAlertDialog == null) {
                    mClearAlertDialog = new CommonAlertDialog(this);
                    mClearAlertDialog.setDialogListener(mOnClearDialogListener);
                    mClearAlertDialog.setTitle(getResources().getString(R.string.myxsm_ordering_reset));
                    mClearAlertDialog.setSureMsg(getResources().getString(R.string.myxsm_ordering_reset_btn));
                }
                mClearAlertDialog.setMsg(getResources().getString(R.string.myxsm_ordering_reset_msg));
                mClearAlertDialog.show();
                break;
        }
    }

    private OnAlertDialogListener mOnClearDialogListener = new OnAlertDialogListener() {
        @Override
        public void onSure() {
            super.onSure();
            mClearAlertDialog.dismiss();
            mPresenter.reset();
            mShopCartRly.setVisibility(View.GONE);
        }

        @Override
        public void onCancel() {
            super.onCancel();
            mClearAlertDialog.dismiss();
        }
    };
    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            skipActivity(XsmOrderingActivity.this, XsmOrdersActivity.class);
        }

        @Override
        public void onRight() {
            super.onRight();
            if (!mPresenter.isHaveCigarettes()) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(XsmOrderingActivity.this, XsmSearchActivity.class);
            startActivityForResult(intent, 0);
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            skipActivity(XsmOrderingActivity.this, XsmOrdersActivity.class);
        }
        return super.onKeyDown(keyCode, event);
    }
}
