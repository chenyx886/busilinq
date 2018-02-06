package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.UserInfoView;
import com.busilinq.data.BaseData;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.presenter.BasePresenter;

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
     * @param userId
     * @param name
     */
    public void getUserInfo(String userId, String name) {
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().getInfo(userId,name), new SubscriberCallBack<UserEntity>() {
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
}
