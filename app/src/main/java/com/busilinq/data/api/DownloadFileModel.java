package com.busilinq.data.api;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author wangshimei
 * @date: 18/1/15
 * @Description: 下载file模型
 */

public class DownloadFileModel {
    private static DownloadFileModel instance = new DownloadFileModel();//单例

    public static DownloadFileModel getInstance() {
        return instance;
    }


    public OkHttpClient client = new OkHttpClient();



    /**
     * post请求
     * @param address
     * @param callback
     * @param map
     */

    public void post(String address, okhttp3.Callback callback, Map<String,String> map)
    {

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null)
        {
            for (Map.Entry<String,String> entry:map.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * get请求
     * @param address
     * @param callback
     */

    public void get(String address, okhttp3.Callback callback)
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
