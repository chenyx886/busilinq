package com.busilinq.presenter;

import android.app.Activity;

import com.busilinq.MApplication;
import com.busilinq.contract.ILoginView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.ulits.AppUtils;
import com.chenyx.libs.utils.Apps;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe： 登录界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView MvpView) {
        super(MvpView);
    }

    /**
     * 登录
     *
     * @param name 手机号或用户名
     * @param password  密码
     */
    public void Login(String name, final String password) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("password", password);
        param.put("deviceId", Apps.getDeviceId(MApplication.getInstance().getApplicationContext()));
        param.put("versionCode", AppUtils.getVersionCode((Activity) MvpView));

        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("登录中");


        addSubscription(RetrofitApiFactory.getMineApi().login(body), new SubscriberCallBack<UserEntity>() {
            @Override
            protected void onSuccess(UserEntity user) {
                MvpView.Success(user);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
