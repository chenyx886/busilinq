package com.busilinq.presenter.mine;


import android.content.Context;

import com.busilinq.contract.mine.IMineView;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.TServiceAccountEntity;
import com.busilinq.presenter.BasePresenter;
import com.busilinq.xsm.data.usercenter.ServiceEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.presenter.UserCenterHelper;
import com.busilinq.xsm.ui.XsmLoginActivity;
import com.chenyx.libs.utils.JumpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：我的 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2017/11/12 下午11:26
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(IMineView MvpView) {
        super(MvpView);
    }

    /**
     * 获取用户资料
     *
     * @param userId
     * @param name
     */
    public void getUserInfo(int userId, String name) {
        addSubscription(RetrofitApiFactory.getMineApi().getInfo(userId, name), new SubscriberCallBack<com.busilinq.data.entity.UserEntity>() {
            @Override
            protected void onSuccess(com.busilinq.data.entity.UserEntity data) {
                MvpView.showUserInfo(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    public void getService(final Context mContext) {
        addSubscription(RetrofitApiFactory.getMineApi().getService(), new SubscriberCallBack<List<TServiceAccountEntity>>() {
            @Override
            protected void onSuccess(List<TServiceAccountEntity> list) {
                UserCenterHelper mHelper = UserCenterHelper.getInstance(mContext);
                UserEntity userEntity = new UserEntity();
                List<ServiceEntity> serviceList = new ArrayList<>();
                for (TServiceAccountEntity entity : list) {
                    ServiceEntity serviceEntity = new ServiceEntity();
                    serviceEntity.setServiceId(entity.getName());
                    serviceEntity.setService(entity.getUrl());
                    serviceEntity.setPermission(entity.getIsEnable());
                    serviceList.add(serviceEntity);
                }
                userEntity.setServiceList(serviceList);
                userEntity.setMemberId("13061501150012");
                //缓存服务URL
                mHelper.saveUser(userEntity);
                JumpUtil.overlay(mContext, XsmLoginActivity.class);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}