package com.busilinq.xsm.data.api;

import android.content.Context;
import android.text.TextUtils;


import com.busilinq.ulits.JsonUtils;
import com.busilinq.xsm.data.entity.pospay.PosAcquirer;
import com.busilinq.xsm.data.entity.pospay.PosPayOrder;
import com.busilinq.xsm.data.entity.pospay.PosPayRecord;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.presenter.UserCenterHelper;
import com.busilinq.xsm.ulits.CommonUtils;
import com.busilinq.xsm.ulits.HttpMethod;
import com.busilinq.xsm.ulits.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by dingyi on 2017/9/5.
 */

public class UmsPosPayApi {

    private UmsPosPayService mService;
    private Retrofit retrofit;

    private static UmsPosPayApi instance;

    private UmsPosPayApi(Context context) {
        String url = "";
        if (UserCenterHelper.getInstance(context).getUser() != null) {
            url = UserCenterHelper.getInstance(context).getUser().getService("unionpay");
        }
        if (TextUtils.isEmpty(url))
            url = "http://10.10.200.163:7071/hkbaccypospay/unionpay/";

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpMethod.instance.getClient(0xF0))
                .baseUrl(url)
                .build();
        mService = retrofit.create(UmsPosPayService.class);
    }

    public static UmsPosPayApi getInstance(Context context) {
        if (CommonUtils.isEmpty(instance))
            instance = new UmsPosPayApi(context);
        return instance;
    }

    public Observable<HttpEntity<PosAcquirer>> acquirer(String sn) {
        return mService.acquirer(sn).subscribeOn(Schedulers.io());
    }

    public Observable<HttpEntity<PosPayOrder>> loadPayOrder(String memId, String custId, String sn, String coNum) {
        Map<String, String> map = new HashMap<>();
        map.put("memId", memId);
        map.put("custId", custId);
        map.put("sn", sn);
        map.put("coNum", coNum);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.loadPayOrder(body).subscribeOn(Schedulers.io());
    }

    public Observable<HttpEntity<PosPayOrder>> loadPayingPayOrder(String memId, String custId, String sn, String coNum) {
        Map<String, String> map = new HashMap<>();
        map.put("memId", memId);
        map.put("custId", custId);
        map.put("sn", sn);
        map.put("coNum", coNum);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.loadPayingPayOrder(body).subscribeOn(Schedulers.io());
    }

    /**
     * 解锁订单
     *
     * @param memId
     * @param custId
     * @param sn
     * @param coNum
     * @return
     */
    public Observable<HttpEntity<PosPayOrder>> unLockedOrder(String memId, String custId, String sn, String coNum) {
        Map<String, String> map = new HashMap<>();
        map.put("memId", memId);
        map.put("custId", custId);
        map.put("sn", sn);
        map.put("coNum", coNum);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.unLockedOrder(body).subscribeOn(Schedulers.io());
    }

    public Observable<HttpEntity<String>> updatePayOrder(String memId, PosPayOrder payOrder, String sn, String state) {
        Map<String, String> map = new HashMap<>();
        map.put("memId", memId);
        map.put("custId", payOrder.getCustId());
        map.put("sn", sn);
        map.put("coNum", payOrder.getCoNum());
        map.put("transNo", payOrder.getTransNo());
        map.put("state", state);
        Logger.e(map.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.updatePayOrder(body).subscribeOn(Schedulers.io());
    }

    /**
     * 百富应答包转换
     *
     * @param memId
     * @param sn
     * @param custId
     * @param coNum
     * @param transNo
     * @return
     */
    public Observable<HttpEntity<String>> record(String memId, String sn, String custId, String coNum, String transNo) {
        Map<String, String> map = new HashMap<>();
        map.put("memId", memId);//MemberId
        map.put("custId", custId);//新商盟帐号
        map.put("sn", sn);//机身码
        map.put("coNum", coNum);//新商盟订单号
        map.put("transNo", transNo);//交易流水号
        Logger.e(JsonUtils.map2json(map));
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.record(body).subscribeOn(Schedulers.io());
    }

    /**
     * 原银联商务的通知方法实体，勿删，作比较用  yu
     *
     * @param memId
     * @param custId
     * @param sn
     * @param coNum
     * @return
     */
//    public Observable<HttpEntity<String>> record(String memId, String sn, String custId, String coNum, String transNo, PosTradeResult result) {
//        Map<String, String> map = new HashMap<>();
//        map.put("memId", memId);//MemberId
//        map.put("custId", custId);//新商盟帐号
//        map.put("sn", sn);//机身码
//        map.put("coNum", coNum);//新商盟订单号
//
//        map.put("resultCode", result.getResCode());//返回码
//        map.put("resultMsg", result.getResDesc());//返回信息描述
//        map.put("pan", result.getCardNo());//卡号
//        map.put("transAmt", result.getAmt());//金额
//        map.put("transTime", result.getTime());//应该是交易时间  09:59:05
//        map.put("transRefNo", result.getRefNo());//参考号
//        map.put("transVoucherNo", result.getTraceNo());//凭证号
//        map.put("transNo", transNo);//交易流水号
//        map.put("posId", result.getTerminalNo());//终端编号
//        Logger.e(JsonUtils.map2json(map));
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
//        return mService.record(body).subscribeOn(Schedulers.io());
//    }
    public Observable<HttpEntity<PosPayRecord>> queryPayOrder(String memId, String custId, String sn, String coNum) {
        Map<String, String> map = new HashMap<>();
        map.put("custId", custId);
        map.put("sn", sn);
        map.put("memId", memId);
        map.put("coNum", coNum);
        Logger.e(map.toString());
        return mService.queryPayOrder(map).subscribeOn(Schedulers.io());
    }
}
