package com.busilinq.ui.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.mine.ISetNewPwdView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.presenter.mine.SetNewPwdPresenter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Company：华科建邺
 * Class Describe：设置新密码
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:44
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SetNewPwdActivity extends BaseMvpActivity<SetNewPwdPresenter> implements ISetNewPwdView {
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     * 新密码
     */
    @BindView(R.id.et_news_pwd)
    EditText mNewsPwd;
    /**
     * 确认新密码
     */
    @BindView(R.id.et_confirm_pwd)
    EditText mConfirmPwd;

    private String phone = "";

    @Override
    protected SetNewPwdPresenter createPresenter() {
        return new SetNewPwdPresenter(this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_set_new_pwd);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        mTitle.setText("设置新密码");
        phone = getIntent().getStringExtra("phone");
    }


    @OnClick({R.id.tv_back, R.id.btn_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;

            case R.id.btn_confirm:
                if (TextUtils.isEmpty(mNewsPwd.getText().toString())) {
                    Toasts.showShort(mContext, "请输入新密码");
                    mNewsPwd.setFocusable(true);
                    mNewsPwd.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mConfirmPwd.getText().toString())) {
                    Toasts.showShort(mContext, "请再次输入新密码");
                    mConfirmPwd.setFocusable(true);
                    mConfirmPwd.requestFocus();
                    return;
                }

                if (!TextUtils.equals(mNewsPwd.getText().toString(), mConfirmPwd.getText().toString())) {
                    Toasts.showShort(this, "两次密码输入不一致");
                    return;
                }
                mPresenter.forgetPassword(phone, mConfirmPwd.getText().toString().trim());
                break;

        }
    }

    @Override
    public void Success() {
        ToastUtils.showShort("修改成功");
        UserCache.clear();
        MApplication.getInstance().appManager.finishAllActivity();
        JumpUtil.overlay(mContext, LoginActivity.class);


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
