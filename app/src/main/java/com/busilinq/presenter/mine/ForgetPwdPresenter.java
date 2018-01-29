package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.IForgetPwdView;
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
 * Class Describe：忘记密码
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:15
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class ForgetPwdPresenter extends BasePresenter<IForgetPwdView> {

    public ForgetPwdPresenter(IForgetPwdView MvpView) {
        super(MvpView);
    }


    /**
     * 获取验证码
     *
     * @param phone
     */
    public void getCode(String phone) {
        Map<String, Object> param = new HashMap<>();
        param.put("phone", phone);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("发送中");
        addSubscription(RetrofitApiFactory.getMineApi().getCode(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.Success(1, "已发送");
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
