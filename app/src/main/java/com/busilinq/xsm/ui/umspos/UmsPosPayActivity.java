package com.busilinq.xsm.ui.umspos;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.data.entity.pospay.PosPayOrder;
import com.busilinq.xsm.presenter.umspos.UmsPosPayPresenter;
import com.busilinq.xsm.ui.XsmOrdersActivity;
import com.busilinq.xsm.ulits.DateUtil;
import com.busilinq.xsm.ulits.StringParse;
import com.busilinq.xsm.viewinterface.IUmsPosPayView;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.HeadBar;
import com.busilinq.xsm.widget.interf.OnHeadBarListener;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by dingyi on 2017/9/5.
 */

public class UmsPosPayActivity extends XsmBaseActivity implements IUmsPosPayView {
    @BindView(R.id.ums_pos_pay_head)
    HeadBar head;
    @BindView(R.id.ums_pos_pay_comp)
    TextView comp;
    @BindView(R.id.ums_pos_pay_xsm_account)
    TextView account;
    @BindView(R.id.ums_pos_pay_xsm_name)
    TextView xsmName;
    @BindView(R.id.ums_pos_pay_xsm_order)
    TextView xsmOrder;
    @BindView(R.id.ums_pos_pay_trans_no)
    TextView transNo;
    @BindView(R.id.ums_pos_pay_trans_amt)
    TextView transAmt;
    @BindView(R.id.ums_pos_pay_valid_time)
    TextView validTime;
    @BindView(R.id.ums_pos_pay_order_pay)
    Button pay;
    @BindView(R.id.ums_pos_pay_error_layout)
    EmptyLayout error;
    @BindView(R.id.ums_pos_pay_container)
    LinearLayout container;

    private CountDownTimer timer;
    private UmsPosPayPresenter presenter;

    @Override
    public int initContentView() {
        return R.layout.ums_pos_pay;
    }

    @Override
    public void initData() {
        presenter = new UmsPosPayPresenter();
        presenter.attachView(this);
        presenter.start();
    }


    @Override
    public void initUi() {
        head.setLeftMsg(mContext.getResources().getString(R.string.back));
        head.setMidMsg(mContext.getResources().getString(R.string.xsm_pay_title));
        head.setOnHeadBarListener(mOnHeadBarListener);
        showLoading(View.VISIBLE);
    }

    @Override
    public void showLoading(int visibility) {
        if (View.VISIBLE == visibility) {
            error.setVisibility(View.VISIBLE);
            container.setVisibility(View.GONE);
        } else {
            error.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showLoadingError(int errorType) {
        error.setErrorType(errorType);
    }

    @Override
    public void updatePayInfoViews(PosPayOrder order) {
        comp.setText(order.getAcquirer().getMerchantName());
        account.setText(order.getCustId());
        xsmName.setText(order.getCustName());
        xsmOrder.setText(order.getCoNum());
        transNo.setText(order.getTransNo());
        transAmt.setText(StringParse.formatMoney(order.getAmount()));
    }

    @Override
    public void updateValidTimeView(String time) {
        validTime.setEnabled(true);
        timer = new CountDownTimer(Long.valueOf(time) * 1000, 1000) {
            @Override
            public void onTick(long l) {
                validTime.setText(DateUtil.second2TimeSecond(l / 1000));
            }

            @Override
            public void onFinish() {
                validTime.setText("付款操作失效，请重新付款！");
                validTime.setEnabled(false);
                pay.setText("重新进行付款操作");
            }
        }.start();
    }

    private void cancelValidTimeView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    @Override
    protected void onDestroy() {
        cancelValidTimeView();//定时器不关闭倒是返回的页面错误退出
        presenter.cancel();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        String str = data.getExtras().getString("pay_result");
        presenter.prnResult(str);

    }

    @OnClick({R.id.ums_pos_pay_order_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ums_pos_pay_order_pay:
                if (null != timer)
                    timer.cancel();
                if (validTime.isEnabled()) {
                    presenter.paying();
                    validTime.setEnabled(false);
                } else {
                    presenter.btnClicked();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!validTime.isEnabled()) {
                presenter.btnClicked();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private OnHeadBarListener mOnHeadBarListener = new OnHeadBarListener() {
        @Override
        public void onLeft() {
            super.onLeft();
            if (!validTime.isEnabled()) {
                presenter.btnClicked();
            } else
                skipActivity(UmsPosPayActivity.this, XsmOrdersActivity.class);
        }
    };


}
