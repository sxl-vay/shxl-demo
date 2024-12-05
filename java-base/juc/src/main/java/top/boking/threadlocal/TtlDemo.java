package top.boking.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TtlDemo {
    public static void main(String[] args) {

        Executor ttlExecutor = TtlExecutors.getTtlExecutor(new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)));
        TransmittableThreadLocal<String> local = new TransmittableThreadLocal<>();
        local.set("test");
        local.set("test2");
        ttlExecutor.execute(() -> {
            System.out.println(local.get());
        });
    }
}
