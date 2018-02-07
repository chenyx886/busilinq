package com.busilinq.xsm.ui;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.presenter.XsmLoginPresenter;
import com.busilinq.xsm.viewinterface.IXsmLoginView;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yu on 2017/2/28.
 */

public class XsmLoginActivity extends XsmBaseActivity implements IXsmLoginView {

    public static final int REQUEST = 1;

    @BindView(R.id.myxsm_login_headbar)
    HeadBar mHeadbar;
    @BindView(R.id.myxsm_login_account_edt)
    EditText mAccountEdt;
    @BindView(R.id.myxsm_login_password_edt)
    EditText mPasswordEdt;
    @BindView(R.id.myxsm_login_province_name_tv)
    TextView mProvinceTv;
    @BindView(R.id.myxsm_login_firm_name_tv)
    TextView mFirmTv;
    @BindView(R.id.myxsm_login_account_iv)
    ImageView mAccountsIv;

    private XsmLoginPresenter mPresenter;

    @Override
    public int initContentView() {
        return R.layout.myxsm_login_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmLoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {

        mHeadbar.setMidMsg(mContext.getResources().getString(R.string.myxsm_authorize));
        mHeadbar.setLeftMsg(mContext.getResources().getString(R.string.back));
        mHeadbar.setOnHeadBarListener(mOnHeadBarListener);
        mPresenter.start();
        if (mPresenter.getMerchant() != null)
            mAccountEdt.setText(mPresenter.getMerchant().getCustCode());
    }

    @Override
    protected void onDestroy() {
        mPresenter.cancel();
        super.onDestroy();
    }

    @Override
    public void updateProvinceView(String province) {
        mProvinceTv.setText(province);
    }

    @Override
    public void updateCompView(String comp) {
        mFirmTv.setText(comp);
    }

    @Override
    public void updateAccoutView(String account) {
        if (mAccountEdt.getText().equals(account))
            return;
        mAccountEdt.setText(account);
        mAccountEdt.setSelection(account.length());
        mPasswordEdt.setText("");
    }

    @Override
    public void updateAccoutsIv(boolean isCheck) {
        if (isCheck)
            mAccountsIv.setVisibility(View.VISIBLE);
        else
            mAccountsIv.setVisibility(View.INVISIBLE);
        mAccountsIv.setEnabled(isCheck);
    }


    @OnClick({R.id.myxsm_login_province_lly, R.id.myxsm_login_firm_lly, R.id.myxsm_login_btn, R.id.myxsm_login_account_iv, R.id.img_qr})
    public void onClick(View view) {
        int id = view.getId();
        hideKeyboard();
        switch (id) {
            case R.id.myxsm_login_province_lly:
            case R.id.myxsm_login_firm_lly:
            case R.id.myxsm_login_account_iv:
                mPresenter.showOptionsPicker(id);
                break;
            case R.id.myxsm_login_btn:
                mPresenter.authorize(mAccountEdt.getText().toString(), mPasswordEdt.getText().toString());
                break;
            case R.id.img_qr:
//                showActivity(XsmLoginActivity.this, QrActivity.class);
                break;
        }
    }

    //关闭软键盘
    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            finish();
        }
    };

}
