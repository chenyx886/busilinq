package com.busilinq.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.busilinq.R;
import com.busilinq.data.BaseData;
import com.busilinq.data.api.MineApi;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.entity.TServiceAccountEntity;
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

    @BindView(R.id.container)
    FrameLayout mContainer;

    /**
     * 浏览器
     */
    private WebView mWebView;

    private String url = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webview);
        unbinder = ButterKnife.bind(this);
        url = "http://testh5.ejavashops.com";
        initData();
    }


    private void initData() {
        // 防止内存泄露
        mWebView = new WebView(this);
        mContainer.addView(mWebView);
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        // 应用可以有缓存
        settings.setAppCacheEnabled(true);
        //防止跳转到浏览器
        mWebView.setWebViewClient(mWebViewClient);
        //支持alert弹窗
        mWebView.setWebChromeClient(mWebChromeClient);

        mWebView.addJavascriptInterface(this, "nativeMethod");
        mWebView.loadUrl(url);
//        mWebView.loadUrl("file:///android_asset/index.html");

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mContainer != null)
            mContainer.removeAllViews();
        mWebView.destroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
    }


    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return false;
            }
            view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    };


    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            mTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }
    };


}
