package com.busilinq.xsm.presenter.umspos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import com.busilinq.xsm.data.api.UmsPosPayApi;
import com.busilinq.xsm.data.api.XsmApi;
import com.busilinq.xsm.data.api.XsmDbApi;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.ParseRespEntity;
import com.busilinq.xsm.data.entity.pospay.PosPayOrder;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.presenter.UserCenterHelper;
import com.busilinq.xsm.presenter.XsmBasePresenter;
import com.busilinq.xsm.ui.XsmOrdersActivity;
import com.busilinq.xsm.ui.umspos.UmsPosPayResultActivity;
import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.viewinterface.IUmsPosPayView;
import com.unionpay.UPPayAssistEx;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by dingyi on 2017/9/5.
 */

public class UmsPosPayPresenter extends XsmBasePresenter<IUmsPosPayView> {


    private UserCenterHelper helper;
    private Merchant merchant;
    private Order currentOrder;
    private PosPayOrder payOrder;
    private UmsPosPayApi payApi;
    private XsmApi xsmApi;

    private String posSn;

    @Override
    public void attachView(IUmsPosPayView view) {
        super.attachView(view);
        this.helper = UserCenterHelper.getInstance(mContext);
        this.merchant = new XsmDbApi(ACache.get(mContext)).getMerchant();
        posSn = merchant.getCustCode();
        this.payApi = UmsPosPayApi.getInstance(mContext);
        this.xsmApi = XsmApi.getInstance(mContext);
    }

    @Override
    public void start() {
        getCurOrder();
    }


    public void paying() {
        // TODO: 2018/1/30 s3：:01表示测试环境  00正式环境 
        UPPayAssistEx.startPay(((Activity) mContext), null, null, payOrder.getTransNo(), "01");
    }

    public void btnClicked() {
        mBaseView.skipActivity(((Activity) mContext), XsmOrdersActivity.class);
    }

    /**
     * 刷卡 返回状态
     *
     * @param result
     */
    public void prnResult(final String result) {
        boolean isSuccess = false;
        //成功查询
        if (result.equalsIgnoreCase("success"))
            isSuccess = true;
        payResult(isSuccess);


    }

    private void payResult(final boolean isSuccess) {
        mBaseView.showProgressDialog("正在更新，请稍后");
        Subscription subscription = payApi.record(helper.getUser().getMemberId(), posSn,
                payOrder.getCustId(), payOrder.getCoNum(), payOrder.getTransNo())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<String>>() {
                    @Override
                    public void call(HttpEntity<String> httpEntity) {
                        mBaseView.closeProgressDialog();
                        ParseRespEntity result = new ParseRespEntity();
                        Bundle bundle = new Bundle();
                        if (httpEntity.isSuccess()) {
                            result.setRspCode(isSuccess ? "0" : "1");
                        } else {
                            result.setRspCode(httpEntity.getCode());
                        }
                        result.setRspMsg(httpEntity.getMsg());
                        bundle.putSerializable(UmsPosPayResultActivity.class.getName(), result);
                        mBaseView.skipActivity(((Activity) mContext), UmsPosPayResultActivity.class, bundle);
                    }
                }, getThrowableAction(""));
        ;
        mSubscriptions.add(subscription);
    }

    private void getCurOrder() {
        mBaseView.showLoading(View.VISIBLE);
        Subscription subscription = xsmApi.current(merchant.getCustCode())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<Order>>() {
                    @Override
                    public void call(HttpEntity<Order> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            currentOrder = httpEntity.getData();
                            loadPayOrder(currentOrder, currentOrder.getPmtStatus().equals("0") ? false : true);
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                            ((Activity) mContext).onBackPressed();
                        }
                    }
                }, getThrowableAction(""));
        mSubscriptions.add(subscription);
    }

    private void loadPayOrder(Order order, boolean paying) {
        Observable<HttpEntity<PosPayOrder>> reqPayOrder = null;
        if (paying) {
            reqPayOrder = payApi.loadPayingPayOrder(helper.getUser().getMemberId(), merchant.getCustCode(),
                    posSn, order.getCoNum());
        } else {
            reqPayOrder = payApi.loadPayOrder(helper.getUser().getMemberId(), merchant.getCustCode(),
                    posSn, order.getCoNum());
        }
        Subscription subscription = reqPayOrder.observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<PosPayOrder>>() {
                    @Override
                    public void call(HttpEntity<PosPayOrder> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            payOrder = httpEntity.getData();
                            mBaseView.updatePayInfoViews(payOrder);
                            mBaseView.updateValidTimeView(payOrder.getValidTime());
                            mBaseView.showLoading(View.GONE);
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                            ((Activity) mContext).onBackPressed();
                        }
                    }
                }, getThrowableAction(""));
        mSubscriptions.add(subscription);
    }


}
