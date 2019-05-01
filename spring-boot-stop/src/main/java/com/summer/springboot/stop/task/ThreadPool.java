package com.summer.springboot.stop.task;

import java.util.concurrent.*;

public class ThreadPool {
    private static ExecutorService pool = new ThreadPoolExecutor(11, 11,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void addTask(Runnable r) {
        pool.execute(r);
    }
}
