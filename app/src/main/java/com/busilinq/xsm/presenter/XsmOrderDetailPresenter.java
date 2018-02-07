package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.view.View;


import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.ui.XsmHistoryOrdersActivity;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;
import com.busilinq.xsm.widget.EmptyLayout;
import com.busilinq.xsm.widget.IXsmOrderDetailView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yu on 2017/6/19.
 */

public class XsmOrderDetailPresenter extends XsmBasePresenter<IXsmOrderDetailView> {

    private Merchant mMerchant;
    private Order mOrder;
    private XsmOrderDetailAdapter mAdapter;
    private boolean isCurrentOrder = false;

    @Override
    public void attachView(IXsmOrderDetailView view) {
        super.attachView(view);
        this.mMerchant = mXsmDbApi.getMerchant();
        isCurrentOrder = ((Activity) mContext).getIntent().getBooleanExtra(XsmHistoryOrdersActivity.class.getName(), false);
        this.mOrder = (Order) ((Activity) mContext).getIntent().getSerializableExtra(Order.class.getSimpleName());
    }

    @Override
    public void start() {
        getOrderDetail();
    }


    private void getOrderDetail() {
        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = mXsmApi.getOrderDetail(mMerchant.getCustCode(), mOrder.getCoNum())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<OrderDetail>>>() {
                    @Override
                    public void call(HttpEntity<List<OrderDetail>> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            mOrder.setDetails(httpEntity.getData());
                            initList();
                            mBaseView.updateOrderInfo(mOrder);
                            mBaseView.showLoading(View.GONE);
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                            mBaseView.showLoadingError(EmptyLayout.NODATA);
                        }
                    }
                }, getThrowableAction("04011"));
        mSubscriptions.add(subscription);
    }

    private void initList() {
        if (null == mAdapter) {
            mAdapter = new XsmOrderDetailAdapter(mContext, mOrder.getDetails());
            mBaseView.updateListView(mAdapter);
        } else {
            mAdapter.setDetails(mOrder.getDetails());
        }
        mBaseView.updateView(mOrder, isCurrentOrder);
    }

    public Order getmOrder() {
        return mOrder;
    }

}
