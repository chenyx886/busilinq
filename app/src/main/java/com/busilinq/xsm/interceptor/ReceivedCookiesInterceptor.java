package com.busilinq.xsm.interceptor;



import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.ulits.ThreadManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by dingyi on 2016/11/30.
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    private ACache mACache;
    private int mServiceId;
    private HashMap<Integer,String> mCookies;

    public ReceivedCookiesInterceptor(ACache aCache, int mServiceId) {
        super();
        this.mACache = aCache;
        this.mServiceId = mServiceId;
    }

    public void setServiceId(int serviceId){
        this.mServiceId = serviceId;
    }

    public void setCookies(HashMap<Integer,String> cookies){
        this.mCookies = cookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            List<String> strs = originalResponse.headers("Set-Cookie");
            saveCookies(strs.get(0).split(";")[0]);
        }
        return originalResponse;
    }

    private void saveCookies(String cookie){

        if(mServiceId == 0xFF){
            return;
        }

        String localCookie = mCookies.get(mServiceId);
        if(null == localCookie || !localCookie.equals(cookie)) {
            mCookies.put(mServiceId,cookie);
        }
        ThreadManager.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                mACache.put("COOKIES",mCookies);
            }
        });
    }
}
