package com.busilinq.data.api;


import com.busilinq.BuildConfig;
import com.busilinq.MApplication;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Company：华科建邺
 * Class Describe：Api 工厂类
 * Create Person：Chenyx
 * Create Time：2017/11/12 下午11:26
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class RetrofitApiFactory {

    //公网地址
//    private static String BASE_URL = "http://mall.busilinq.com";
    //测试
    public static String BASE_URL = "http://www.busilinq.com:8002";

    /**
     * 代理接口构建类
     */
    public static Retrofit mRetrofit;
    /**
     * Http 连接超时
     */
    private static int CONNECT_TIMEOUT = 20;
    /**
     * Http 读取超时
     */
    private static int READ_TIMEOUT = 20;
    /**
     * Http 写入超时
     */
    private static int WRITE_TIMEOUT = 20;
    /**
     * 缓存大小
     */
    private static int CACHE_SIZE = 10 * 1024 * 1024;


    /**
     * 创建 retrofit
     *
     * @return
     */
    public static Retrofit retrofit() {

        if (mRetrofit == null) {


            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                //日志拦截器
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                httpBuilder.addInterceptor(logging);
            }

            httpBuilder.cache(new Cache(MApplication.getAppContext().getCacheDir(), CACHE_SIZE))
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true); // 失败重试

            OkHttpClient okHttpClient = httpBuilder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    //首页模块接口
    public static HomeApi getHomeApi() {
        return retrofit().create(HomeApi.class);
    }

    //分类模块接口
    public static ClassifyApi getClassifyApi() {
        return retrofit().create(ClassifyApi.class);
    }

    //购物车模块接口
    public static CartApi getCartApi() {
        return retrofit().create(CartApi.class);
    }

    //订单模块接口
    public static OrderApi getOrderApi() {
        return retrofit().create(OrderApi.class);
    }

    //我的模块接口
    public static MineApi getMineApi() {
        return retrofit().create(MineApi.class);
    }

}
