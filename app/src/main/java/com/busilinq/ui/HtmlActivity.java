package com.busilinq.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Company：华科建邺
 * Class Describe：带头部的网页
 * Create Person：Chenyx
 * Create Time：2018/2/03 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class HtmlActivity extends BaseActivity {


    public static final int REQUEST = 1;

    /**
     * 返回
     */
    @BindView(R.id.tv_back)
    TextView mBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView mTitle;
    /**
     *
     */
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.il_header_layout)
    View mIlHeaderLayout;

    /**
     * 浏览器
     */
    private WebView mWebView;

    private String url = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webview);
    }


    @Override
    protected void initUI() {
        mBack.setVisibility(View.VISIBLE);
        mIlHeaderLayout.setVisibility(View.VISIBLE);
        mTitle.setText("首页");
        url = getIntent().getStringExtra("url");
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
        mWebView.loadUrl(url);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            setResult(REQUEST, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_back, R.id.tv_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.tv_close:
                finish();
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mContainer != null)
            mContainer.removeAllViews();
        mWebView.destroy();
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
            mTitle.setText(TextUtils.isEmpty(title) ? "" : title);
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

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {
    }
}
