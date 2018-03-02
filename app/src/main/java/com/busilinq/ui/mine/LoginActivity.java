package com.busilinq.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.busilinq.R;
import com.busilinq.base.BaseMvpActivity;
import com.busilinq.contract.ILoginView;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.data.event.RefreshCartEvent;
import com.busilinq.presenter.LoginPresenter;
import com.busilinq.widget.MLoadingDialog;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;

import org.greenrobot.eventbus.EventBus;

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


    public static final int REQUEST = 1;

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
        setTranslucentStatus(R.color.body_bg);
    }

    @Override
    protected void initUI() {
    }


    @OnClick({R.id.tv_login, R.id.tv_forget_password, R.id.tv_register})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_forget_password:
                bundle.putString("title", "忘记密码");
                JumpUtil.overlay(LoginActivity.this, GetCodeActivity.class, bundle);
                break;
            case R.id.tv_register:
                bundle.putString("title", "注册");
                JumpUtil.overlay(LoginActivity.this, GetCodeActivity.class, bundle);
                break;
            case R.id.tv_login:
                String name = tvPhone.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("请输入手机号或用户名");
                    tvPhone.requestFocus();
                    return;
                }
                String password = tvPwd.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("请输入密码");
                    tvPwd.requestFocus();
                    return;
                }
                mPresenter.Login(name, password);
                break;
        }


    }

    @Override
    public void Success(UserEntity user) {
        //缓存用户信息
        UserCache.put(user);
        //刷新购物车
        EventBus.getDefault().post(new RefreshCartEvent());
        Intent intent = new Intent();
        setResult(REQUEST, intent);
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