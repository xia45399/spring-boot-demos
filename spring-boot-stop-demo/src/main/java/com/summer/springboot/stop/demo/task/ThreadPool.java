package com.summer.springboot.stop.demo.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private static ExecutorService pool = new ThreadPoolExecutor(11, 11,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void addTask(Runnable r) {
        pool.execute(r);
    }
}
