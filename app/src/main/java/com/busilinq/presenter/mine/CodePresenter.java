package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.ICodeView;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.CodeEntity;
import com.busilinq.presenter.BasePresenter;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：获取（验证码）和验证（验证码）
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午1:15
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CodePresenter extends BasePresenter<ICodeView> {

    public CodePresenter(ICodeView MvpView) {
        super(MvpView);
    }


    /**
     * 获取验证码
     *
     * @param type  必填 0:注册 1:修改密码
     * @param phone
     */
    public void getCode(final int type, String phone) {
        MvpView.showProgress("发送中");
        addSubscription(RetrofitApiFactory.getMineApi().getCode(type, phone), new SubscriberCallBack<BaseData<CodeEntity>>() {
            @Override
            protected void onSuccess(BaseData<CodeEntity> data) {
                MvpView.Success("");
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 验证验证码
     *
     * @param type  必填 0:注册 1:修改密码
     * @param phone 手机号
     * @param code  验证码
     */
    public void verifyCode(int type, String phone, String code) {
        param.put("type", type);
        param.put("phone", phone);
        param.put("code", code);
        RequestBody body = JsonRequestBody.createJsonBody(param);

        MvpView.showProgress("发送中");
        addSubscription(RetrofitApiFactory.getMineApi().verifyCode(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.Success("");
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
