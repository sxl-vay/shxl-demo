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

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = build();
        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                long startTime = System.currentTimeMillis();
                long duration = 10 * 1000; // 10 seconds in milliseconds
                double result = 0;

                System.out.println("Computation started...");

                // Perform intensive computations until 10 seconds have passed
                while (System.currentTimeMillis() - startTime < duration) {
                    // Example of a computationally intensive task
                    for (int j = 0; j < 1_000_000; j++) {
                        result += Math.sin(j) * Math.cos(j); // Perform trigonometric operations
                    }
                }

                System.out.println("Computation finished. Result: " + result);
            });

        }
    }

    private static Thread createThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(Thread.currentThread().getName()+"shxl:::");
        System.out.println("thread = " + thread);
        return thread;
    }
}
