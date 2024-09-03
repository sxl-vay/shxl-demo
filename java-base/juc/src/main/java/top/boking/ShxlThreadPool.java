package top.boking;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author shxl
 * @Date 2024/9/1 20:43
 * @Version 1.0
 */
public class ShxlThreadPool {
    public static ThreadPoolExecutor build() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2
                , 2
                , 1
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue<>()
                , ShxlThreadPool::createThread
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        System.out.println("shxlLLL = " + args);
    }

    private static Thread createThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(Thread.currentThread().getName()+"shxl:::");
        System.out.println("thread = " + thread);
        return thread;
    }
}
