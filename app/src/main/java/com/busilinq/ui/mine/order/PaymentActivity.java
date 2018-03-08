package com.busilinq.ui.mine.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.order.IPaymentView;
import com.busilinq.presenter.mine.order.PaymentPresenter;
import com.busilinq.ui.ToDevelopedActivity;
import com.busilinq.widget.IconTextItem;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：yuzhenxing
 * Create Time：2018/3/1 14:55
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class PaymentActivity extends BaseMvpActivity<PaymentPresenter> implements IPaymentView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 银联支付
     */
    @BindView(R.id.it_pay_unionpay)
    IconTextItem itPayUnionpay;
    /**
     * 微信支付
     */
    @BindView(R.id.it_pay_wechat)
    IconTextItem itPayWechat;
    /**
     * 支付宝支付
     */
    @BindView(R.id.it_pay_alipay)
    IconTextItem itPayAlipay;
    /**
     * 标题
     */
    @BindView(R.id.it_transfer_accounts)
    IconTextItem itTransferAccounts;
    private int orderId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment);
        orderId = getIntent().getIntExtra(PaymentActivity.class.getSimpleName(), -1);
    }

    @OnClick({R.id.tv_back, R.id.it_pay_unionpay, R.id.it_pay_wechat, R.id.it_pay_alipay, R.id.it_transfer_accounts})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.it_pay_unionpay:
                JumpUtil.overlay(this, ToDevelopedActivity.class);
                break;
            case R.id.it_pay_wechat:
                JumpUtil.overlay(this, ToDevelopedActivity.class);
                break;
            case R.id.it_pay_alipay:
                payAlipayOrder();
                break;
            case R.id.it_transfer_accounts:
                JumpUtil.overlay(this, ToDevelopedActivity.class);
                break;
        }
    }

    private void payAlipayOrder() {
        mPresenter.payAlipayOrder(orderId);
    }

    @Override
    protected PaymentPresenter createPresenter() {
        return new PaymentPresenter(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText("选择支付方式");
    }

    @Override
    public void PaySuccess() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }


}
