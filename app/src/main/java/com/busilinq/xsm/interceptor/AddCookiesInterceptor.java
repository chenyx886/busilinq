package com.busilinq.xsm.interceptor;



import com.busilinq.xsm.ulits.ACache;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dingyi on 2016/11/30.
 */

public class AddCookiesInterceptor implements Interceptor {

    private ACache mACache;
    private int mServiceId;

    public AddCookiesInterceptor(ACache aCache, int serviceId) {
        this.mACache = aCache;
        this.mServiceId = serviceId;
    }

    public void setServiceId(int serviceId){
        this.mServiceId = serviceId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = getCookie();
        if(null != cookie && !cookie.equals(""))
            builder.addHeader("Cookie", getCookie());
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Accept", "application/json");
        return chain.proceed(builder.build());
    }

    private String getCookie(){
        String cookie = null;
        if(mServiceId == 0xFF){
            return  null;
        }
        HashMap<Integer,String> cookies = (HashMap<Integer, String>)mACache.getAsObject("COOKIES");
        if(null != cookies)
            cookie = cookies.get(mServiceId);
        return cookie;
    }

}
