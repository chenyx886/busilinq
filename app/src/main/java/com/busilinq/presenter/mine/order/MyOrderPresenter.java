package com.busilinq.presenter.mine.order;

import com.busilinq.contract.mine.order.IMyOrdersView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.data.entity.OrderEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.List;

/**
 * Created by yu on 2018/1/31.
 */
public class MyOrderPresenter extends BasePresenter<IMyOrdersView> {
    private int limit = 5;

    public MyOrderPresenter(IMyOrdersView MvpView) {
        super(MvpView);
    }

    /**
     * 订单列表
     * @param userId
     * @param page
     */
    public void getOrdersList(int userId, int page) {
        addSubscription(RetrofitApiFactory.getOrderApi().getOrders(userId, page, limit), new SubscriberCallBack<PageEntity<HomeOrderEntity>>() {
            @Override
            protected void onSuccess(PageEntity<HomeOrderEntity> list) {
                MvpView.OrdersList(list);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }
}
