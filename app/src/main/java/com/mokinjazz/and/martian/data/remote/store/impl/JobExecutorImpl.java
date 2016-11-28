package com.mokinjazz.and.martian.data.remote.store.impl;

import android.os.Looper;

import com.mokinjazz.and.martian.data.remote.store.JobExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Job Executor implementation.
 *  Uses {@link ExecutorService} to run background tasks
 *
 * Created by vvasilyev on 6/20/16.
 */
public class JobExecutorImpl implements JobExecutor {

    private static final int THREAD_COUNT = 3;
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());

    @Override
    public void runAsync(Runnable task) {
        executorService.execute(task);
    }

    @Override
    public void runOnUi(Runnable task) {
        handler.post(task);
    }
}
