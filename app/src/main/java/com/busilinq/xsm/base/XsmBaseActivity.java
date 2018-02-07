package com.busilinq.xsm.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.busilinq.R;
import com.busilinq.ulits.BugGoutAgent;
import com.busilinq.xsm.viewinterface.IBaseView;
import com.busilinq.xsm.widget.CustomProgressDialog;
import com.chenyx.libs.app.AppManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.testin.agent.Bugout;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/10 上午11:18
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public abstract class XsmBaseActivity extends AutoLayoutActivity implements IBaseView {

    protected Context mContext;
    private CustomProgressDialog mProgressDialog;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BugGoutAgent.init(this, false);
        setContentView(initContentView());
        setTranslucentStatus(R.color.color_0d9e5c);
        mContext = this.getContext();
        initInjector();
        initData();
        initUi();
        AppManager.getAppManager().addActivity(this);
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
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
        finish();
    }

    /**
     * 设置View
     *
     * @return
     */
    protected abstract int initContentView();

    /**
     * 注入Injector
     */
    public void initInjector() {
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化UI
     */
    protected abstract void initUi();

    @Override
    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
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

    @Override
    public void showPrompt(String msg) {

    }


    @Override
    public void closePrompt() {

    }

    @Override
    public void showProgressDialog(String msg) {
        if (null == mProgressDialog) {
            mProgressDialog = new CustomProgressDialog(mContext);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        if (null != mProgressDialog)
            mProgressDialog.dismiss();
    }

    @Override
    public void showLoading(int visibility) {

    }

    @Override
    public void showLoadingError(int errorType) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRefresh(boolean bRefresh) {

    }

    @Override
    public void showActivity(Activity activity, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

    }

    @Override
    public void showActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    public void showActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    public void skipActivity(Activity activity, Class<?> cls, Bundle bundle) {
        showActivity(activity, cls, bundle);
        activity.finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    public void skipActivity(Activity activity, Class<?> cls) {
        showActivity(activity, cls);
        activity.finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    public void skipActivity(Activity activity, Intent intent) {
        showActivity(activity, intent);
        activity.finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

}
