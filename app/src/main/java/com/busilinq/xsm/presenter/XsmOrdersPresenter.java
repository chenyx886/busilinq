package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import com.busilinq.xsm.data.api.UmsPosPayApi;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.data.entity.pospay.PosPayRecord;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.ui.XsmHistoryOrdersActivity;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;
import com.busilinq.xsm.ui.umspos.UmsPosPayActivity;
import com.busilinq.xsm.ulits.DateUtil;
import com.busilinq.xsm.ulits.XsmOrderComparator;
import com.busilinq.xsm.viewinterface.IXsmOrdersView;
import com.busilinq.xsm.widget.EmptyLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by yu on 2017/6/16.
 * 1.获得order 2.获得详情. 3.获得限量
 */

public class XsmOrdersPresenter extends XsmBasePresenter<IXsmOrdersView> {
    private Merchant mMerchant;
    private Order mCurrentOrder;
    private XsmOrderDetailAdapter mAdapter;
    private UmsPosPayApi payApi;
    private List<Order> mHistoryOrders = new ArrayList<>();
    private String posSn = "";

    @Override
    public void attachView(IXsmOrdersView view) {
        super.attachView(view);
        this.payApi = UmsPosPayApi.getInstance(mContext);
        this.mMerchant = mXsmDbApi.getMerchant();
        posSn = mMerchant.getCustCode();
    }

    @Override
    public void start() {
        getOrders();
    }

    public void deleteOrder() {
        if (mCurrentOrder == null)
            return;
        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = mXsmApi.delOrder(mMerchant.getCustCode(), mCurrentOrder.getCoNum())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<Object>>() {
                    @Override
                    public void call(HttpEntity<Object> httpEntity) {
                        mBaseView.showLoading(View.GONE);
                        mBaseView.toast(httpEntity.getMsg());
                        if (httpEntity.isSuccess()) {
                            start();
                        }
                    }
                }, getThrowableAction("04014"));
        mSubscriptions.add(subscription);
    }


    public void payment() {
        mBaseView.showActivity((Activity) mContext, UmsPosPayActivity.class);
    }

    public void queryPayOrder() {
        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = payApi.queryPayOrder(UserCenterHelper.getInstance(mContext).getUser().getMemberId(),
                mMerchant.getCustCode(), posSn, mCurrentOrder.getCoNum())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<PosPayRecord>>() {
                    @Override
                    public void call(HttpEntity<PosPayRecord> httpEntity) {
                        mBaseView.showLoading(View.GONE);
                        if (httpEntity.isSuccess()) {
                            getOrders();
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction(""));
        mSubscriptions.add(subscription);
    }


    private void getOrders() {
        mBaseView.showLoading(View.VISIBLE);
        String bDate = DateUtil.getDateFromNow(Calendar.DAY_OF_YEAR, -365, "yyyyMMdd");
        String eDate = DateUtil.getDateFromNow(Calendar.DAY_OF_YEAR, 0, "yyyyMMdd");
//        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = mXsmApi.getOrders(mMerchant.getCustCode(), bDate, eDate)
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Order>>>() {
                    @Override
                    public void call(HttpEntity<List<Order>> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            List<Order> mOrders = httpEntity.getData();
                            Collections.sort(mOrders, new XsmOrderComparator());
                            getCurrentOrder(mOrders);
                            if (mCurrentOrder != null) {
                                getOrderDetail();
                            } else {
                                mBaseView.showLoading(View.GONE);
                                mBaseView.updateCurrentOrder(mCurrentOrder, 0, 0);
                            }
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                            mBaseView.showLoadingError(EmptyLayout.NODATA);
                        }
                    }
                }, getThrowableAction("04010"));
        mSubscriptions.add(subscription);
    }

    //获得订单详情
    private void getOrderDetail() {
        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = mXsmApi.getOrderDetail(mMerchant.getCustCode(), mCurrentOrder.getCoNum())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<OrderDetail>>>() {
                    @Override
                    public void call(HttpEntity<List<OrderDetail>> httpEntity) {
                        mBaseView.showLoading(View.GONE);
                        if (httpEntity.isSuccess()) {
                            mCurrentOrder.setDetails(httpEntity.getData());
                            initListView();
                            mBaseView.updateCurrentOrder(mCurrentOrder, mAdapter.getNumber(), mAdapter.getTotal());
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction("04011"));
        mSubscriptions.add(subscription);
    }

    //取得当期订单
    private void getCurrentOrder(List<Order> mOrders) {
        Order order = mOrders.get(0);
        if (order.getOrderDate().equals(mMerchant.getOrderDate())) {
            mCurrentOrder = order;
            mOrders.remove(0);
        } else {
            mCurrentOrder = null;
        }
        mHistoryOrders = mOrders;
    }

    private void initListView() {
        if (null == mAdapter) {
            mAdapter = new XsmOrderDetailAdapter(mContext, mCurrentOrder.getDetails());
            mBaseView.updateList(mAdapter);
        } else {
            mAdapter.setDetails(mCurrentOrder.getDetails());
        }
    }

    public Merchant getMerchant() {
        return mXsmDbApi.getMerchant();
    }

    public Order getCurrentOrder() {
        return mCurrentOrder;
    }

    public void showHistoryOrders() {
        if (mHistoryOrders != null && mHistoryOrders.size() > 0) {
            Bundle bundle = new Bundle();
            boolean isCurrentOrder = false;
            if (mCurrentOrder != null && !mCurrentOrder.getPmtStatus().equals("0"))
                isCurrentOrder = true;
            bundle.putSerializable(XsmHistoryOrdersActivity.class.getName(),
                    isCurrentOrder);
            bundle.putSerializable(Order.class.getName(), (Serializable) mHistoryOrders);
            mBaseView.showActivity((Activity) mContext, XsmHistoryOrdersActivity.class, bundle);
        } else
            mBaseView.toast("没有订单数据");
    }


}
