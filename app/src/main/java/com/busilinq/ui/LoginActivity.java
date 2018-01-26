package com.busilinq.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.ILoginView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.LoginPresenter;
import com.busilinq.ulits.AppUtils;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.Toasts;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Company：华科建邺
 * Class Describe： 登录
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements ILoginView {
    /**
     * 手机号
     */
    @BindView(R.id.et_phone)
    EditText tvPhone;
    /**
     * 密码
     */
    @BindView(R.id.et_pwd)
    EditText tvPwd;
    /**
     * 当前版本号
     */
    @BindView(R.id.tv_VersionName)
    TextView mVersionName;

    @Override
    protected LoginPresenter createPresenter() {
        if (null == mPresenter) {
            mPresenter = new LoginPresenter(this);
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        setTranslucentStatus(R.color.white);
    }

    @Override
    protected void initUI() {
        mVersionName.setText("V" + AppUtils.getVersionName(MApplication.getInstance()));
    }


    @OnClick({  R.id.tv_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                String phone = tvPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() != 11) {
                    Toasts.showShort(LoginActivity.this, "请输入手机号");
                    tvPhone.requestFocus();
                    return;
                }
                String pwd = tvPwd.getText().toString();
                if (TextUtils.isEmpty(pwd)) {
                    Toasts.showShort(LoginActivity.this, "请输入密码");
                    tvPwd.requestFocus();
                    return;
                }
                //跳转主页面
                Bundle bundle = new Bundle();
                bundle.putBoolean(LoginActivity.class.getSimpleName(), false);
                JumpUtil.overlay(this, MainActivity.class, bundle);

//                mPresenter.Login(phone, pwd);
                break;
        }


    }

    @Override
    public void Success(UserEntity user) {

        //缓存用户信息
        UserCache.put(user);
        //跳转主页面
        Bundle bundle = new Bundle();
        bundle.putBoolean(LoginActivity.class.getSimpleName(), false);
        JumpUtil.overlay(this, MainActivity.class, bundle);
        finish();
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