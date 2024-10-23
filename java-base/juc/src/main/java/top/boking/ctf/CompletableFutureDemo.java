package top.boking.ctf;

import top.boking.ShxlThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CompletableFuture.join 同步等待获取结果
 * CompletableFuture.get 同上无异常版本
 * CompletableFuture.handle 异步处理结果，返回一个新的结果对象，着重于异常处理
 * CompletableFuture.thenApply 应用：接受本对象的结果，同时返回一个新对象
 * CompletableFuture.thenAccept 接收：接受本对象的结果，但是返回一个Void的对象（就是不返回结果）
 * CompletableFuture.thenRun 执行：不接受本对象的结果，只返回一个Void对象，相当于本对象任务完成后，单纯跑一段业务逻辑
 * CompletableFuture.thenCombine 组合将本对象任务结果和输入的对象任务结果进行组合
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor shxlPool = ShxlThreadPool.build();
        // 创建三个异步任务
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(1); // 模拟耗时操作
            return "Result 1";
        },shxlPool);
        future1.complete("Result 1 手动完成");
        future1.handle((r, e) -> {
            System.out.println("e = " + e);
            return r+"handle重置结果，并不会覆盖原值";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(2); // 模拟耗时操作
            return "Result 2";
        },shxlPool);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            sleep(3); // 模拟耗时操作
            return "Result 3";
        },shxlPool);

        System.out.println("allOf start");
        // 使用 allOf 来等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
        System.out.println("allOf end");

        //等待所有任务完成
       /* Void join = allFutures.join();
        System.out.println("join = " + join);*/


        System.out.println("allFutures.isDone() = " + allFutures.isDone());
        // 当所有任务完成后，打印提示
        allFutures.thenRun(() -> {
            System.out.println("All futures are completed!:thenRun");
            // 如果想获取每个future的结果，可以分别调用 join() 方法
            System.out.println(future1.join());
            System.out.println(future2.join());
            System.out.println(future3.join());
        });

        allFutures.thenAccept(result -> {
            System.out.println("All futures are completed!thenAccept:"+result);
            // 如果想获取每个future的结果，可以分别调用 join() 方法
            System.out.println(future1.join());
            System.out.println(future2.join());
            System.out.println(future3.join());
        });

        CompletableFuture<Integer> stringCompletableFuture = allFutures.thenApply(result -> {
            System.out.println("All futures are completed!thenAccept:" + result);
            // 如果想获取每个future的结果，可以分别调用 join() 方法
            System.out.println(future1.join());
            System.out.println(future2.join());
            System.out.println(future3.join());
            return 111;
        });

        //等所有任务完成后，手动设置就会失败，手动设置只能在任务完成之前成功
        /*
        allFutures.join();
        System.out.println("future2.complete(\"Result 2 手动设置尝试\") = " + future2.complete("Result 2 手动设置尝试"));
        */
        System.out.println("finish");
        // 保证主线程不会过早退出
        sleep(10);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
