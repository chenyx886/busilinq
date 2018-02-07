package com.busilinq.xsm.data.api;



import com.busilinq.xsm.data.entity.pospay.PosAcquirer;
import com.busilinq.xsm.data.entity.pospay.PosPayOrder;
import com.busilinq.xsm.data.entity.pospay.PosPayRecord;
import com.busilinq.xsm.data.usercenter.HttpEntity;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by dingyi on 2017/9/5.
 */

public interface UmsPosPayService {
    @POST("sign")
    Observable<HttpEntity<String>> sign(@Body RequestBody body);

    @GET("acquirer")
    Observable<HttpEntity<PosAcquirer>> acquirer(@Query("sn") String sn);

    @POST("loadPayOrder")
    Observable<HttpEntity<PosPayOrder>> loadPayOrder(@Body RequestBody body);

    @POST("unLockedOrder")
    Observable<HttpEntity<PosPayOrder>> unLockedOrder(@Body RequestBody body);

    @POST("loadPayingOrder")
    Observable<HttpEntity<PosPayOrder>> loadPayingPayOrder(@Body RequestBody body);//加载待付款订单

    @POST("updatePayOrder")
    Observable<HttpEntity<String>> updatePayOrder(@Body RequestBody body);

    @POST("record")
    Observable<HttpEntity<String>> record(@Body RequestBody body);

    @GET("queryPayOrder")
    Observable<HttpEntity<PosPayRecord>> queryPayOrder(@QueryMap Map<String, String> map);
}
