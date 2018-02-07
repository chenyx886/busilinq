package com.busilinq.xsm.data.api;


import com.busilinq.xsm.data.entity.AppUpdateInfo;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dingyi on 2016/11/30.
 */

public interface UserCenterService {

    @POST("member/login")
    Observable<HttpEntity<UserEntity>> login(@Body RequestBody body);

    @POST("member/modify")
    Observable<HttpEntity<Object>> modify(@Body RequestBody body);

//    @POST("member")
//    Observable<HttpEntity<Object>> register(@Body RequestBody body);

    @POST("member/challenge")
    Observable<HttpEntity<Object>> verificationCode(@Body RequestBody body);

    @POST("member/finish")
    Observable<HttpEntity<Object>> registerFinish(@Body RequestBody body);

    @POST("member/cell")
    Observable<HttpEntity<Object>> registerCell(@Body RequestBody body);

    @PUT("member")
    Observable<HttpEntity<Object>> update(@Body RequestBody body);

    @DELETE("member")
    Observable<HttpEntity<UserEntity>> delete(@Body RequestBody body);

    @PUT("member/rebind")
    Observable<HttpEntity<UserEntity>> rebind(@Body RequestBody body);

    @GET("member/challenge")
    Observable<HttpEntity<Object>> challenge(@Query("cell") String cell);

    @GET("member/report")
    Observable<HttpEntity<Object>> report(@Query("cell") String cell, @Query("memberId") String memberId, @Query("equipOS") String equipOS, @Query("equipNo") String equipNo, @Query("jpushCode") String jpushCode);

    @POST("member/password")
    Observable<HttpEntity<Object>> password(@Body RequestBody body);

    @GET("member/version")
    Observable<HttpEntity<AppUpdateInfo>> version(@Query("memberId") String cell, @Query("os") String os);
}
