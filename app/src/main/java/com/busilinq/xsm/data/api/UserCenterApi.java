package com.busilinq.xsm.data.api;



import com.busilinq.ulits.JsonUtils;
import com.busilinq.xsm.data.entity.AppUpdateInfo;
import com.busilinq.xsm.data.usercenter.EquipmentEntity;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.ulits.CommonUtils;
import com.busilinq.xsm.ulits.HttpMethod;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;


public class UserCenterApi {
    private String BASE_URL = "http://www.busilinq.com:18084/sso/";

    private UserCenterService mService;
    private Retrofit retrofit;
    private static UserCenterApi instance;

    private UserCenterApi() {

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpMethod.instance.getClient(0xF0))
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(UserCenterService.class);
    }

    private UserCenterApi(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(UserCenterService.class);
    }

    public static UserCenterApi getInstance() {
        if(CommonUtils.isEmpty(instance))
            instance = new UserCenterApi();
        return instance;
    }

    public static UserCenterApi newInstance(OkHttpClient client){
        return new UserCenterApi(client);
    }

    /**
     * 接口编码001
     *
     * @param username
     * @param password
     * @param equipNo
     * @param version
     * @return
     */
    public Observable<HttpEntity<UserEntity>> login(String username, String password, String equipNo, String version) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("equipNo", equipNo);
        map.put("version", version);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.login(body).subscribeOn(Schedulers.io());
    }


    /**
     * 接口编码002(验证验证码)
     *
     * @param memberId
     * @param challenge
     * @return
     */
    public Observable<HttpEntity<Object>> verificationCode(String memberId, String challenge) {
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("challenge", challenge);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.verificationCode(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码003(注册完成)
     *
     * @return
     */
    public Observable<HttpEntity<Object>> registerFinish(String memberId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("memberCredentials", null);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.registerFinish(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码003(注册手机号)
     *
     * @param phone
     * @param password
     * @return
     */
    public Observable<HttpEntity<Object>> registerCell(String phone, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cell", phone);
        map.put("password", password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.registerCell(body).subscribeOn(Schedulers.io());
    }

    public Observable<HttpEntity<Object>> modify(UserEntity userEntity) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cell", userEntity.getCell());
        map.put("realName", userEntity.getRealName());
        map.put("userName", userEntity.getUserName());
        map.put("address", userEntity.getAddress());
        map.put("gender", (userEntity.getGender().equals("男"))?"1":"0");
        map.put("area", userEntity.getArea());
        map.put("remark", null);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.modify(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码004
     *
     * @param map
     * @return
     */
    public Observable<HttpEntity<Object>> update(Map<String, String> map) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.update(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码005
     *
     * @param phone
     * @return
     */
    public Observable<HttpEntity<Object>> challenge(String phone) {
        return mService.challenge(phone).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码006
     *
     * @param entity
     * @return
     */
    public Observable<HttpEntity<Object>> report(EquipmentEntity entity) {
        return mService.report(entity.getCell(), entity.getMemberId(), entity.getEquipOS(), entity.getEquipNo(), entity.getJpushCode()).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码007
     *
     * @param phone
     * @param challenge
     * @param password
     * @return
     */
    public Observable<HttpEntity<Object>> password(String phone, String challenge, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cell", phone);
        map.put("challenge", challenge);
        map.put("password", password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.map2json(map));
        return mService.password(body).subscribeOn(Schedulers.io());
    }

    /**
     * 接口编码008
     *
     * @param memberId
     * @return
     */
    public Observable<HttpEntity<AppUpdateInfo>> version(String memberId) {
        return mService.version(memberId,"android").subscribeOn(Schedulers.io());
    }

}