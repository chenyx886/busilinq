package com.busilinq.presenter.cart;

import com.busilinq.contract.cart.INewlyAddedAddress;
import com.busilinq.data.BaseData;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.BasePresenter;
import com.busilinq.ulits.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：新增收货地址
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:36
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class NewlyAddedAddressPresenter extends BasePresenter<INewlyAddedAddress> {
    public NewlyAddedAddressPresenter(INewlyAddedAddress mvpView) {
        super(mvpView);
    }

    /**
     * 添加收货地址
     * @param userId
     * @param entity
     */
    public void addAddress(String userId, UserShopAddrEntity entity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("company",entity.getCompany());
        jsonObject.addProperty("cell",entity.getCell());
        jsonObject.addProperty("name",entity.getName());
        jsonObject.addProperty("specificAddr",entity.getSpecificAddr());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().addAddress(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.addAddressSuccess();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }

    /**
     * 编辑收货地址
     * @param userId
     * @param entity
     */
    public void editAddress(String userId, UserShopAddrEntity entity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("addrId", entity.getAddrId());
        jsonObject.addProperty("company",entity.getCompany());
        jsonObject.addProperty("cell",entity.getCell());
        jsonObject.addProperty("name",entity.getName());
        jsonObject.addProperty("specificAddr",entity.getSpecificAddr());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getMineApi().editAddress(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.addAddressSuccess();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }
}
