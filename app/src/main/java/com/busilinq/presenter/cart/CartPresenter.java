package com.busilinq.presenter.cart;

import com.busilinq.contract.cart.ICartView;
import com.busilinq.contract.home.IMainView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.SysConfig;

import java.util.List;

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
     * @param page
     */
    public void getOrderList(int page) {
        addSubscription(RetrofitApiFactory.getCartApi().cart(page, SysConfig.limit), new SubscriberCallBack<PageEntity<HomeGoodsEntity>>() {
            @Override
            protected void onSuccess(PageEntity<HomeGoodsEntity> HGoodsList) {
                MvpView.CartList(HGoodsList);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }


}
