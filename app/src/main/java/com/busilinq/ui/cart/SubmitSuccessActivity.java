package com.busilinq.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;
import com.busilinq.ui.mine.order.MyOrdersActivity;
import com.busilinq.ui.mine.order.PaymentActivity;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：订单提交成功提示
 * Create Person：wenxin.li
 * Create Time：2018/1/31 12:42
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SubmitSuccessActivity extends BaseActivity {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_submit_success);
    }

    @Override
    protected void initUI() {
        mTitle.setText("提交成功");
    }


    @OnClick({R.id.tv_back, R.id.btn_view_order, R.id.btn_go_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_view_order:
                JumpUtil.overlay(this, MyOrdersActivity.class);
                finish();
                break;
            case R.id.btn_go_pay:
                JumpUtil.overlay(this, PaymentActivity.class);
                finish();
                break;

        }
    }

    @Override
    public void showProgress(String message) {
        MLoadingDialog.show(this, message);
    }

    @Override
    public void hideProgress() {
        MLoadingDialog.dismiss();
    }
}