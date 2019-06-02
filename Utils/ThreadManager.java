package com.jiyun.day15;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by $lzj on 2019/3/22.
 */
public class ThreadManager {

    private static ThreadManager threadManager;
    private final ThreadPoolExecutor mExecutor;

    private ThreadManager(){

        mExecutor = new ThreadPoolExecutor(5,//核心线程数量,核心池的大小
                20,//线程池最大线程数
                30,//表示线程没有任务执行时最多保持多久时间会终止
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<Runnable>(),//任务队列,用来存储等待执行的任务
                Executors.defaultThreadFactory(),//线程工厂,如何去创建线程的
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static ThreadManager getThreadManager() {
        if (threadManager == null){
            synchronized (ThreadManager.class){
                if (threadManager == null){
                    threadManager = new ThreadManager();
                }
            }
        }
        return threadManager;
    }

    /**
     * 执行任务
     */
    public void execute(Runnable runnable){
        if(runnable==null)return;

        mExecutor.execute(runnable);
    }
    /**
     * 从线程池中移除任务
     */
    public void remove(Runnable runnable){
        if(runnable==null)return;

        mExecutor.remove(runnable);
    }
}
