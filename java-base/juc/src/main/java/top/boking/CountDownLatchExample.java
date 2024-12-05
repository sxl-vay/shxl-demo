package top.boking;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个计数器为 3 的 CountDownLatch
        CountDownLatch latch = new CountDownLatch(3);

        // 创建三个线程
        for (int i = 1; i <= 3; i++) {
            int threadNum = i;
            new Thread(() -> {
                try {
                    System.out.println("线程 " + threadNum + " 正在执行任务...");
                    Thread.sleep((long) (Math.random() * 1000)); // 模拟任务执行时间
                    System.out.println("线程 " + threadNum + " 完成任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 每个线程完成任务后调用 countDown()
                    latch.countDown();
                }
            }).start();
        }

        // 主线程等待，直到计数器为 0
        System.out.println("主线程等待所有子线程完成任务...");
        latch.await();
        System.out.println("所有子线程已完成，主线程继续执行");
    }
}
