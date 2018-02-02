package com.busilinq.presenter.cart;

import com.busilinq.contract.cart.ISubmitOrderView;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：购物车提交
 * Create Person：wenxin.li
 * Create Time：2018/1/31 12:42
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class SubmitOrderPresenter extends BasePresenter<ISubmitOrderView>{
    public SubmitOrderPresenter(ISubmitOrderView mvpView) {
        super(mvpView);
    }

    public void getDeaaultAddress(String userId) {
        MvpView.showProgress("获取中...");
        addSubscription(RetrofitApiFactory.getCartApi().getDefaultAddress(userId), new SubscriberCallBack<UserShopAddrEntity>() {
            @Override
            protected void onSuccess(UserShopAddrEntity response) {
                MvpView.getDefaultAddress(response);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }

}
