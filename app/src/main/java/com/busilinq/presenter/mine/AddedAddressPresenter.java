package com.busilinq.presenter.mine;

import com.busilinq.contract.mine.IAddedAddress;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

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

public class AddedAddressPresenter extends BasePresenter<IAddedAddress> {

    public AddedAddressPresenter(IAddedAddress mvpView) {
        super(mvpView);
    }

    /**
     * 添加收货地址
     *
     * @param entity
     */
    public void addAddress(UserShopAddrEntity entity) {

        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("company", entity.getCompany());
        param.put("cell", entity.getCell());
        param.put("name", entity.getName());
        param.put("province", entity.getProvince());
        param.put("city", entity.getCity());
        param.put("district", entity.getDistrict());
        param.put("specificAddr", entity.getSpecificAddr());
        param.put("isDefault", entity.getIsDefault());
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("操作中...");
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
     *
     * @param entity
     */
    public void editAddress(UserShopAddrEntity entity) {

        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("addrId", entity.getAddrId());
        param.put("company", entity.getCompany());
        param.put("cell", entity.getCell());
        param.put("name", entity.getName());
        param.put("province", entity.getProvince());
        param.put("city", entity.getCity());
        param.put("district", entity.getDistrict());
        param.put("specificAddr", entity.getSpecificAddr());
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("修改中...");
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
