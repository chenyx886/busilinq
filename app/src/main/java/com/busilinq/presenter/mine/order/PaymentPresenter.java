package com.busilinq.presenter.mine.order;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.busilinq.contract.mine.order.IPaymentView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.PayResult;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.OrderEntity;
import com.busilinq.data.entity.payAlipayEntity;
import com.busilinq.presenter.BasePresenter;
import com.chenyx.libs.utils.Toasts;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：yuzhenxing
 * Create Time：2018/3/1 14:55
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class PaymentPresenter extends BasePresenter<IPaymentView> {
    Context mContext;
    private static final int SDK_PAY_FLAG = 1;
    private int orderId;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000") || TextUtils.equals(resultStatus, "8000") || TextUtils.equals(resultStatus, "6004")) {
                        seachOrder();
                    } else {
                        Toasts.showShort(mContext, payResult.getMemo());
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    private void seachOrder() {
        MvpView.showProgress("正在查询支付结果...");
        addSubscription(RetrofitApiFactory.getOrderApi().isPayed(1, orderId), new SubscriberCallBack<OrderEntity>() {
            @Override
            protected void onSuccess(OrderEntity entity) {
                if (entity != null && entity.getOrderId() == orderId) {
                    MvpView.PaySuccess();
                } else {
                    Toasts.showShort(mContext, "支付失败");
                }
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }

    public PaymentPresenter(IPaymentView mvpView) {
        super(mvpView);
        mContext = (Context) mvpView;
    }

    public void payAlipayOrder(int payOrderId) {
        MvpView.showProgress("正在执行...");
        this.orderId = payOrderId;
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("merchantId", 1);
        param.put("orderId", orderId);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(RetrofitApiFactory.getOrderApi().payAliPay(body), new SubscriberCallBack<payAlipayEntity>() {
            @Override
            protected void onSuccess(final payAlipayEntity entity) {
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask((Activity) mContext);
                        Map<String, String> result = alipay.payV2(entity.getParam(), true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
