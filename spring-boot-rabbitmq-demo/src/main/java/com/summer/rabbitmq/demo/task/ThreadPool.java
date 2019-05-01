package com.summer.rabbitmq.demo.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void addTask(Runnable r) {
        pool.execute(r);
    }
}
