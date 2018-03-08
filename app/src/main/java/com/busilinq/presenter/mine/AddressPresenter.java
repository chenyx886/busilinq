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

    /**
     * 地址列表
     *
     * @param userId
     */
    public void getAddressList(int userId) {
        addSubscription(RetrofitApiFactory.getMineApi().getAddressList(userId), new SubscriberCallBack<List<UserShopAddrEntity>>() {
            @Override
            protected void onSuccess(List<UserShopAddrEntity> response) {
                MvpView.showAddressList(response);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }

    /**
     * 设置默认地址
     *
     * @param userId
     * @param addrId
     */
    public void setDefaultAddress(final int userId, int addrId) {
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

    /**
     * 删除地址
     *
     * @param userId
     * @param addrId
     */
    public void deletedAddress(final int userId, int addrId) {
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
