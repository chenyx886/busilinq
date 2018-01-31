package com.busilinq.presenter.mine.order;

import com.busilinq.contract.mine.order.IMyOrdersView;
import com.busilinq.presenter.BasePresenter;

/**
 * Created by yu on 2018/1/31.
 */
public class MyOrderPresenter extends BasePresenter<IMyOrdersView> {

    public MyOrderPresenter(IMyOrdersView MvpView) {
        super(MvpView);
    }
}
