package com.busilinq.xsm.data.api;



import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.data.entity.Limit;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.data.entity.Organize;
import com.busilinq.xsm.data.entity.XsmRandom;
import com.busilinq.xsm.data.usercenter.HttpEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yi.ding on 2016/8/9.
 */

public interface XsmService {
    @GET("ruids/next")
    Observable<HttpEntity<XsmRandom>> random(@Query("sn") String sn);

    @GET("organize")
    Observable<HttpEntity<List<Organize>>> organize(@Query("id") String id);

    @POST("authorize")
    Observable<HttpEntity<Merchant>> authorize(@Body RequestBody body);

    @GET("merchant")
    Observable<HttpEntity<Merchant>> getMerchant(@Query("custId") String custId);

    @GET("merchant/balance")
    Observable<HttpEntity<Object>> getBalance(@Query("custId") String custId);

    @GET("merchant/limit")
    Observable<HttpEntity<Limit>> getLimit(@Query("custId") String custId);

    @GET("cigarette/list")
    Observable<HttpEntity<List<Cigarette>>> getCigarettes(@Query("custId") String custId);

    @GET("cigarette/limit")
    Observable<HttpEntity<List<Cigarette>>> getCgtLmts(@Query("custId") String custId, @Query("orderDate") String orderDate, @Query("cgtCodes") String cgtCodes);

    @GET("cart")
    Observable<HttpEntity<List<Cart>>> getCart(@Query("custId") String custId);

    @POST("cart")
    Observable<HttpEntity<Merchant>> addCart(@Body RequestBody body);

    @DELETE("cart")
    Observable<HttpEntity<Merchant>> deleteCart(@Body RequestBody body);

    @GET("bills/current")
    Observable<HttpEntity<Order>> current(@Query("custId") String custId);

    @GET("bills/his/head")
    Observable<HttpEntity<List<Order>>> getOrders(@Query("custId") String custId, @Query("begin") String beginDate, @Query("end") String endDate);

    @GET("bills/his/lines")
    Observable<HttpEntity<List<OrderDetail>>> getOrderDetail(@Query("custId") String custId, @Query("billsNo") String coNum);

    @POST("bills/submit")
    Observable<HttpEntity<Object>> addOrder(@Body RequestBody body);

    @POST("bills/update")
    Observable<HttpEntity<Object>> updateOrder(@Body RequestBody body);

    @DELETE("bills")
    Observable<HttpEntity<Object>> deleteOrder(@Query("custId") String custId, @Query("billsNo") String coNum);

    @GET("favorite")
    Observable<HttpEntity<List<Cigarette>>> getFavorites(@Query("custId") String custId);

    @POST("favorite")
    Observable<HttpEntity<Object>> addFavorite(@Body RequestBody body);

    @DELETE("favorite")
    Observable<HttpEntity<Object>> deleteFavorite(@Query("custId") String custId, @Query("cgtCode") String cgtCode);
}

