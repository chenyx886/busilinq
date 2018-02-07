package com.busilinq.xsm.data.api;

import android.content.Context;

import com.busilinq.ulits.JsonUtils;
import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.data.entity.Limit;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.data.entity.Organize;
import com.busilinq.xsm.data.entity.XsmRandom;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.presenter.UserCenterHelper;
import com.busilinq.xsm.ulits.CommonUtils;
import com.busilinq.xsm.ulits.HttpMethod;
import com.busilinq.xsm.ulits.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by yi.ding on 2016/8/9.
 */
public class XsmApi {
    private XsmService mService;
    private Retrofit retrofit;
    private static XsmApi instance;

    private XsmApi(Context context) {
        String BASE_URL = UserCenterHelper.getInstance(context).getUser().getService("baccy");
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpMethod.instance.getClient(0xF0))
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(XsmService.class);
    }


    public static XsmApi getInstance(Context context) {
        if (CommonUtils.isEmpty(instance))
            instance = new XsmApi(context);
        return instance;
    }

    /**
     * 接口编码001
     *
     * @param id
     * @return
     */
    public Observable<HttpEntity<List<Organize>>> organize(String id) {
        return mService.organize(id).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 002
     *
     * @param memberId
     * @param orgCode
     * @param custId
     * @param password
     * @return
     */
    public Observable<HttpEntity<Merchant>> authorize(String memberId, String orgCode, String cell, String custId, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("orgCode", orgCode);
        map.put("custId", custId);
        map.put("cell", cell);
        map.put("password", password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.authorize(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 003
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<Merchant>> getMerchant(String custId) {
        return mService.getMerchant(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 004
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<Object>> getBalance(String custId) {
        return mService.getBalance(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 005
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<Limit>> getLimit(String custId) {
        return mService.getLimit(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 006
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<List<Cigarette>>> getCigarettes(String custId) {
        return mService.getCigarettes(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 007
     *
     * @param custId
     * @param orderDate
     * @param cgtCodes
     * @return
     */
    public Observable<HttpEntity<List<Cigarette>>> getCgtLmts(String custId, String orderDate, String cgtCodes) {
        return mService.getCgtLmts(custId, orderDate, cgtCodes).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 008
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<List<Cart>>> getCart(String custId) {
        return mService.getCart(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 009
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<Order>> current(String custId) {
        return mService.current(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 010
     *
     * @param custId
     * @param beginDate
     * @param endDate
     * @return
     */
    public Observable<HttpEntity<List<Order>>> getOrders(String custId, String beginDate, String endDate) {
        return mService.getOrders(custId, beginDate, endDate).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 011
     *
     * @param custId
     * @param coNum
     * @return
     */
    public Observable<HttpEntity<List<OrderDetail>>> getOrderDetail(String custId, String coNum) {
        return mService.getOrderDetail(custId, coNum).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 012
     *
     * @param custId
     * @param orderDate
     * @param details
     * @return
     */
    public Observable<HttpEntity<Object>> addOrder(String custId, String orderDate, List<OrderDetail> details) {
        JsonObject json = new JsonObject();
        json.addProperty("custId", custId);
        json.addProperty("orderDate", orderDate);
        JsonArray aJson = JsonUtils.listToJson(details);
        json.add("details", aJson);
        Logger.e(json.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        return mService.addOrder(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 013
     *
     * @param custId
     * @param orderDate
     * @param coNum
     * @param details
     * @return
     */
    public Observable<HttpEntity<Object>> updateOrder(String custId, String orderDate, String coNum, List<OrderDetail> details) {
        JsonObject json = new JsonObject();
        json.addProperty("custId", custId);
        json.addProperty("orderDate", orderDate);
        json.addProperty("coNum", coNum);
        JsonArray aJson = JsonUtils.listToJson(details);
        json.add("details", aJson);
        Logger.e(json.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        return mService.updateOrder(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 014
     *
     * @param custId
     * @param coNum
     * @return
     */
    public Observable<HttpEntity<Object>> delOrder(String custId, String coNum) {
        return mService.deleteOrder(custId, coNum).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 015
     *
     * @param custId
     * @return
     */
    public Observable<HttpEntity<List<Cigarette>>> getFavorites(String custId) {
        return mService.getFavorites(custId).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 016
     *
     * @param custId
     * @param details
     * @return
     */
    public Observable<HttpEntity<Object>> addFavorite(String custId, List<Cigarette> details) {
        JsonObject json = new JsonObject();
        json.addProperty("custId", custId);
        JsonArray aJson = JsonUtils.listToJson(details);
        json.add("details", aJson);
        Logger.e(json.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        return mService.addFavorite(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码 017
     *
     * @param custId
     * @param cgtCode
     * @return
     */
    public Observable<HttpEntity<Object>> deleteFavorite(String custId, String cgtCode) {
        return mService.deleteFavorite(custId, cgtCode).subscribeOn(Schedulers.io());
    }

    /**
     * 扫码登录中的获取随机数
     *
     * @return
     */
    public Observable<HttpEntity<XsmRandom>> random(String sn) {
        return mService.random(sn).subscribeOn(Schedulers.io());


    }
}
