package com.busilinq.xsm.ulits;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程管理工具，用于处理一步任务
 * @author
 *
 */
public class ThreadManager {
    private final int THREAD_FIXED = 10;
    private static ThreadManager sInstance = null;
    private ExecutorService mExecutorService;

    private ThreadManager() {
        this.mExecutorService = Executors.newFixedThreadPool(THREAD_FIXED);
    }

    public static synchronized ThreadManager getInstance() {
        if (sInstance == null) {
            sInstance = new ThreadManager();
        }
        return sInstance;
    }

    public void submit(Runnable task) {
        this.mExecutorService.submit(task);
    }

    public Future<Integer> submit(Callable<Integer> task) {
        return this.mExecutorService.submit(task);
    }

    public void shutdown() {
        if (!this.mExecutorService.isShutdown())
            this.mExecutorService.shutdownNow();
    }
}