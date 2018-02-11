package com.busilinq.data.api;


import com.busilinq.MApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    //服务器Api地址
//  private static   String BASE_URL = "http://bd.busilinq.com:19090";
    //测试地址
    private static String BASE_URL = "http://mall.busilinq.com";

    /**
     * 代理接口构建类
     */
    public static Retrofit mRetrofit;
    /**
     * Http连接超时
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
            Gson gson = new GsonBuilder()
                    .setLenient()// json宽松
                    .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                    .serializeNulls() //智能null
                    .setPrettyPrinting()// 调教格式
                    .disableHtmlEscaping() //默认是GSON把HTML 转义的
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();


            Retrofit.Builder builder = new Retrofit.Builder();
            builder.addConverterFactory(GsonConverterFactory.create(gson));
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            builder.baseUrl(BASE_URL);
            //日志拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.cache(new Cache(MApplication.getAppContext().getCacheDir(), CACHE_SIZE))
                    .addInterceptor(logging)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true); // 失败重试
            OkHttpClient httpClient = httpBuilder.build();
            builder.client(httpClient);
            mRetrofit = builder.build();
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
