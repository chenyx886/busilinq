package com.busilinq.presenter.cart;


import com.busilinq.contract.cart.ICartView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

import java.math.BigDecimal;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wenxin.li
 * Create Time：2018/2/1 11:44
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CartPresenter extends BasePresenter<ICartView> {

    public CartPresenter(ICartView MvpView) {
        super(MvpView);
    }

    /**
     * 获取订单列表
     *
     * @param page
     */
    public void getOrderList(int page) {
        addSubscription(RetrofitApiFactory.getCartApi().cart(page, SysConfig.limit, UserCache.GetUserId()), new SubscriberCallBack<PageEntity<MainCartEntity>>() {
            @Override
            protected void onSuccess(PageEntity<MainCartEntity> hGoodsList) {
                if (null == hGoodsList) {
                    MvpView.showEmptyView();
                } else {
                    MvpView.CartList(hGoodsList);
                }
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }

    /**
     * @param cartId
     * @param number 修改数量
     * @param price  价格
     */
    public void UpdateCart(final int position, int cartId, int number, BigDecimal price) {
        param.put("cartId", cartId);
        param.put("number", number);
        param.put("price", price);
        RequestBody body = JsonRequestBody.createJsonBody(param);

        addSubscription(RetrofitApiFactory.getCartApi().UpdateCart(body), new SubscriberCallBack<CartEntity>() {
            @Override
            protected void onSuccess(CartEntity cartEntity) {
                MvpView.Success(position, cartEntity);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
