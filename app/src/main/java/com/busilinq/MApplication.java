package com.busilinq;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.chenyx.libs.app.AppManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * Company：华科建邺
 * Class Describe：APP 应用
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MApplication extends Application {


   // gf
    private static MApplication instance;
    /**
     * 线程池
     */
    private ExecutorService backgroundExecutor;
    /**
     * App Activity 自定义栈管理
     */
    public AppManager appManager;

    public MApplication() {
        super();
        instance = this;
        backgroundExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "Background executor service");
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setDaemon(true);
                return thread;
            }
        });

    }

    public static synchronized MApplication context() {
        return (MApplication) getAppContext().getApplicationContext();
    }

    public static MApplication getInstance() {
        if (instance == null)
            throw new IllegalStateException();
        return instance;
    }

    public static Context getAppContext() {
        if (instance == null)
            throw new IllegalStateException();
        return instance.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        appManager = AppManager.getAppManager();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


    /**
     * Submits request to be executed in background.
     *
     * @param runnable
     */
    public void runInBackground(final Runnable runnable) {
        backgroundExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {

                }
            }
        });
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}