package com.busilinq.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.busilinq.ulits.BugGoutAgent;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.testin.agent.Bugout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * Company：华科建邺
 * Class Describe： Activity 基类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected String TAG;

    private CompositeSubscription mCompositeSubscription;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        BugGoutAgent.init(this, false);
        initBind();
        initUI();
        initTAG(this);
        MApplication.getInstance().appManager.addActivity(this);
        setTranslucentStatus();


    }

    /**
     * 沉浸式状态栏
     */
    protected void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MApplication.getInstance().appManager.destroyActivity(this);
        onUnsubscribe();
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
    }

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 日志TAG初始化
     *
     * @param context
     */
    protected void initTAG(Context context) {
        TAG = context.getClass().getSimpleName();
    }

    /**
     * 界面绑定方法，在不需要进行界面绑定的Activity,进行空实现即可
     */
    protected void initBind() {
        unbinder = ButterKnife.bind(this);
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 统一返回
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugout.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugout.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugout.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }
}