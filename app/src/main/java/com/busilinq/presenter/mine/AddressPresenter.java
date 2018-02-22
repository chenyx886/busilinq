package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.AddressListView;
import com.busilinq.data.BaseData;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/1 下午2:36
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class AddressPresenter extends BasePresenter<AddressListView> {

    public AddressPresenter(AddressListView mvpView) {
        super(mvpView);
    }


    public void getAddressList(int userId) {
        MvpView.showProgress("获取中...");
        addSubscription(RetrofitApiFactory.getMineApi().getAddressList(userId), new SubscriberCallBack<List<UserShopAddrEntity>>() {
            @Override
            protected void onSuccess(List<UserShopAddrEntity> response) {
                MvpView.getAddressList(response);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }

    public void setDefaultAddress(final int userId, int addrId) {
        MvpView.showProgress("更新中...");
        addSubscription(RetrofitApiFactory.getMineApi().setDefaultAddress(userId, addrId), new SubscriberCallBack<List<UserShopAddrEntity>>() {
            @Override
            protected void onSuccess(List<UserShopAddrEntity> response) {
                getAddressList(userId);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }

    public void deletedAddress(final int userId, int addrId) {
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().deleteAddress(userId, addrId), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                getAddressList(userId);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }


}
