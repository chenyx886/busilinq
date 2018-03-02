package com.busilinq.presenter.cart;

import com.busilinq.contract.cart.ISubmitOrderView;
import com.busilinq.data.BaseData;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.entity.OrderEntity;
import com.busilinq.data.entity.OrderGoodsPO;
import com.busilinq.data.entity.UserShopAddrEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：购物车提交
 * Create Person：wenxin.li
 * Create Time：2018/1/31 12:42
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class SubmitOrderPresenter extends BasePresenter<ISubmitOrderView> {
    public SubmitOrderPresenter(ISubmitOrderView mvpView) {
        super(mvpView);
    }

    /**
     * 获取默认地址
     *
     * @param userId
     */
    public void getDeaaultAddress(int userId) {
        MvpView.showProgress("获取中...");
        addSubscription(RetrofitApiFactory.getCartApi().getDefaultAddress(userId), new SubscriberCallBack<UserShopAddrEntity>() {
            @Override
            protected void onSuccess(UserShopAddrEntity entity) {
                MvpView.showDefaultAddress(entity);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }

    public void submitOrder(int addressId, String shippingType, String payType, int activityId, String remark, List<OrderGoodsPO> goodsList) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("addressId", addressId);
        param.put("shippingType", shippingType);
        param.put("payType", payType);
        param.put("activityId", activityId);
        param.put("remark", remark);
        param.put("goodsList", goodsList);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getOrderApi().submitOrder(body), new SubscriberCallBack<OrderEntity>() {
            @Override
            protected void onSuccess(OrderEntity data) {
                MvpView.submitSuccess(data);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    /**
     * 删除购物车
     *
     * @param list
     */
    public void deleteCartList(List<MainCartEntity> list) {
        List<Integer> deleteList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            deleteList.add(list.get(i).getCart().getCartId());
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("cartIds", deleteList);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        MvpView.showProgress("加载中...");
        addSubscription(RetrofitApiFactory.getCartApi().deleteCarts(body), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.deleteResult();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }

        });
    }
}
