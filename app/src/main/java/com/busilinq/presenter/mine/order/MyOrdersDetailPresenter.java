package com.busilinq.presenter.mine.order;

import com.busilinq.contract.mine.order.IMyOrdersDetailView;
import com.busilinq.contract.mine.order.IMyOrdersView;
import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.ToastUtils;

/**
 * Created by yu on 2018/1/31.
 */
public class MyOrdersDetailPresenter extends BasePresenter<IMyOrdersDetailView> {

    public MyOrdersDetailPresenter(IMyOrdersDetailView MvpView) {
        super(MvpView);
    }

    /**
     * 订单详情
     * @param orderNum
     */
    public void getOrdersListDetail(String orderNum) {
        addSubscription(RetrofitApiFactory.getOrderApi().getOrdersDetial(UserCache.GetUserId(),orderNum), new SubscriberCallBack<HomeOrderEntity>() {
            @Override
            protected void onSuccess(HomeOrderEntity detail) {
                MvpView.OrdersListDetail(detail);
            }
            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 删除订单
     * @param orderId
     * @param orderNum
     */
    public void deletedOrder(int orderId,String orderNum) {
        addSubscription(RetrofitApiFactory.getOrderApi().deleteOrder(UserCache.GetUserId(), orderId,orderNum), new SubscriberCallBack<BaseData>() {
            @Override
            protected void onSuccess(BaseData data) {
                MvpView.success();
            }
            @Override
            public void onCompleted() {
            }
        });
    }


}
