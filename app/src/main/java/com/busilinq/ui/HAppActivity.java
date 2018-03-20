package com.busilinq.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.busilinq.R;
import com.busilinq.data.BaseData;
import com.busilinq.data.api.MineApi;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.TServiceAccountEntity;
import com.busilinq.ulits.X5WebView;
import com.busilinq.widget.ProgressWebView;
import com.busilinq.xsm.data.usercenter.UserEntity;
import com.busilinq.xsm.presenter.UserCenterHelper;
import com.busilinq.xsm.ui.XsmLoginActivity;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;
import com.chenyx.libs.utils.Toasts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Company：华科建邺
 * Class Describe：H5APP商城
 * Create Person：Chenyx
 * Create Time：2018/3/16 上午9:43
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class HAppActivity extends AppCompatActivity {

    private Unbinder unbinder;

    /**
     * 浏览器
     */
    @BindView(R.id.webView)
    X5WebView mWebView;


    private String url = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webview);
        unbinder = ButterKnife.bind(this);
        url = "http://h5.busilinq.com:8807/h5/";
        initData();

    }


    private void initData() {
        mWebView.addJavascriptInterface(this, "nativeMethod");
        mWebView.loadUrl(url);
//        mWebView.loadUrl("file:///android_asset/index.html");

        mWebView.setOnRefreshEventListener(new ProgressWebView.OnRefreshEventListener() {
            @Override
            public void onFinished() {

            }
        });
    }

    @JavascriptInterface
    public void toActivity(String activityName, final String phone) {
        //此处应该定义常量对应，同时提供给web页面编写者
        if (TextUtils.equals(activityName, "XsmLoginActivity")) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.connectTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS);

            OkHttpClient mOkHttpClient = builder.build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitApiFactory.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//对转换后的数据进行再包装
                    .build();
            retrofit.create(MineApi.class)
                    .getService()//接口调用
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseData<List<TServiceAccountEntity>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showShort(e.getMessage());
                        }

                        @Override
                        public void onNext(final BaseData<List<TServiceAccountEntity>> data) {
                            if (data.getCode().equals("0000")) {
                                UserCenterHelper mHelper = UserCenterHelper.getInstance(HAppActivity.this);
                                UserEntity userEntity = new UserEntity();
                                userEntity.setServiceList(data.getData());
                                userEntity.setMemberId(phone);
                                //缓存服务URL
                                mHelper.saveUser(userEntity);
                                JumpUtil.overlay(HAppActivity.this, XsmLoginActivity.class);
                            } else {
                                Toasts.show(HAppActivity.this, "获取服务地址失败", 0);
                            }
                        }
                    });

        }
    }


    /**
     * 使点击回退按钮不会直接退出整个应用程序而是返回上一个页面
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 确保注销配置能够被释放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            mWebView.destroy();
        }
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
    }
}
