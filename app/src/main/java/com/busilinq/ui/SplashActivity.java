package com.busilinq.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.busilinq.R;
import com.busilinq.data.cache.PrefCache;
import com.busilinq.data.cache.UserCache;
import com.chenyx.libs.utils.JumpUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Company：华科建邺
 * Class Describe：启动界面
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SplashActivity extends RxAppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTranslucentStatus(R.color.splash_bg);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserCache.get() != null) {
                    JumpUtil.overlay(SplashActivity.this, MainActivity.class);
                } else {
                    JumpUtil.overlay(SplashActivity.this, LoginActivity.class);
                }
                finish();

            }
        }, 1500);

    }


    /**
     * 沉浸式状态栏
     */
    protected void setTranslucentStatus(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);
        }
    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}
