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
     * 设置新密码
     *
     * @param phone  手机号
     * @param newPwd 新密码
     */
    public void UpdatePwd(String phone, String newPwd) {
        Map<String, Object> param = new HashMap<>();
        param.put("phone", phone);
        param.put("newPwd", newPwd);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("修改中...");
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
}
