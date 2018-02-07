package com.busilinq.xsm.ulits;


import com.busilinq.MApplication;
import com.busilinq.xsm.interceptor.AddCookiesInterceptor;
import com.busilinq.xsm.interceptor.ReceivedCookiesInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by yi.ding on 2016/8/17.
 */
public class HttpMethod {
    private int HTTP_CONNECT_TIMEOUT = 50;
    private int HTTP_WRITE_TIMEOUT = 10;
    private int HTTP_READ_TIMEOUT = 30;

    private OkHttpClient okHttpClient;

    ACache mACache = ACache.get(MApplication.context());
    private AddCookiesInterceptor mAddCookiesInterceptor = new AddCookiesInterceptor(mACache,-1);
    private ReceivedCookiesInterceptor mReceivedCookiesInterceptor = new ReceivedCookiesInterceptor(mACache,-1);

    public static HttpMethod instance = new HttpMethod();

    private HttpMethod(){

        // Log信息
        //HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        // 公私密匙
        //MarvelSigningInterceptor signingInterceptor = new MarvelSigningInterceptor(
        //        BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(mAddCookiesInterceptor);
        builder.addInterceptor(mReceivedCookiesInterceptor);
        okHttpClient = builder.build();
    }

    public OkHttpClient getClient(int serviceId){
        mAddCookiesInterceptor.setServiceId(serviceId);
        mReceivedCookiesInterceptor.setServiceId(serviceId);
        HashMap<Integer,String> cookies =  (HashMap<Integer, String>) mACache.getAsObject("COOKIES");
        if(null == cookies){
            cookies = new HashMap<>();
        }
        mReceivedCookiesInterceptor.setCookies(cookies);
        return okHttpClient;
    }

    public OkHttpClient getNewClient(int serviceId){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);

        HashMap<Integer,String> cookies =  (HashMap<Integer, String>) ACache.get(MApplication.context()).getAsObject("COOKIES");
        if(null == cookies){
            cookies = new HashMap<>();
        }
        AddCookiesInterceptor addCookiesInterceptor = new AddCookiesInterceptor(mACache,-1);
        ReceivedCookiesInterceptor receivedCookiesInterceptor = new ReceivedCookiesInterceptor(mACache,-1);

        addCookiesInterceptor.setServiceId(serviceId);
        receivedCookiesInterceptor.setServiceId(serviceId);
        receivedCookiesInterceptor.setCookies(cookies);

        builder.addInterceptor(addCookiesInterceptor);
        builder.addInterceptor(receivedCookiesInterceptor);
        return builder.build();
    }
}
