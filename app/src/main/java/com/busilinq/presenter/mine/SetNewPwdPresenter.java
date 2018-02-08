package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.ISetNewPwdView;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：设置新密码
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:15
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SetNewPwdPresenter extends BasePresenter<ISetNewPwdView> {

    public SetNewPwdPresenter(ISetNewPwdView MvpView) {
        super(MvpView);
    }


    /**
     * 忘记密码
     *
     * @param phone    手机号
     * @param password 新密码
     */
    public void forgetPassword(String phone, String password) {
        param.put("phone", phone);
        param.put("password", password);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("重置密码中...");
        addSubscription(RetrofitApiFactory.getMineApi().forgetPassword(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.Success();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
    /**
     * 修改密码
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    public void modifyPassword( String oldPassword,String  newPassword) {
        param.put("oldPassword", oldPassword);
        param.put("newPassword", newPassword);
        RequestBody body = JsonRequestBody.createJsonBody(param);

        MvpView.showProgress("重置密码中...");
        addSubscription(RetrofitApiFactory.getMineApi().modifyPassword(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.Success();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
    /**
     * 注册
     *
     * @param phone    手机号
     * @param password 新密码
     */
    public void register(String phone, String password) {
        Map<String, Object> param = new HashMap<>();
        param.put("phone", phone);
        param.put("password", password);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("注册中...");
        addSubscription(RetrofitApiFactory.getMineApi().register(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.Success();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
