package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.UserInfoView;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/2 下午4:20
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView> {
    public UserInfoPresenter(UserInfoView mvpView) {
        super(mvpView);
    }


    /**
     * 获取用户资料
     *
     * @param userId
     * @param name
     */
    public void getUserInfo(int userId, String name) {
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().getInfo(userId, name), new SubscriberCallBack<UserEntity>() {
            @Override
            protected void onSuccess(UserEntity data) {
                MvpView.getUserInfoSuccess(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 修改用户资料
     *
     * @param user_account
     * @param user_name
     * @param user_email
     * @param user_tell
     */
    public void modifyUserInfo(String user_account, String user_name, String user_email, String user_tell) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("name", user_account);
        param.put("realName", user_name);
        param.put("email", user_email);
        param.put("cell", user_tell);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().modifyUserInfo(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.modifyUserInfoSuccess();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }
}
