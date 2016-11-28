package com.mokinjazz.and.martian.data.remote.store;

/**
 *  Job Executor
 *
 * Created by vvasilyev on 6/25/16.
 */
public interface JobExecutor {
    /**
     *  Runs provided task in background
     *
     * @param task
     */
    void runAsync(Runnable task);

    /**
     *  Runs provided task in main UI thread
     *
     * @param task
     */
    void runOnUi(Runnable task);
}
