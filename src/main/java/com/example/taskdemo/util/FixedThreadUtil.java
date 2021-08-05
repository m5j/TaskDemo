package com.example.taskdemo.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadUtil {

    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);  //固定数量线程池 单例

//    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  //灵活数量线程池

    private FixedThreadUtil() {
    }

    public static void executeTask(Runnable runnable) {
        fixedThreadPool.execute(runnable);
    }
}
