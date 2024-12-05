package top.boking.redismvc.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author shxl
 * @Date 2024/10/15 21:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private Jedis jedis;

    private ThreadPoolExecutor singleThreadEventExecutor = new ThreadPoolExecutor(1,1,0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));

    @GetMapping("/t1")
    public Map<String, Object> queue(String name) throws IOException {
        RSet<Object> set1 = redissonClient.getSet(name);
        set1.add("seta");
        set1.add("setb");
        return new HashMap<>() {{
            put("result", set1);
        }};
    }


    @GetMapping("/getLock")
    public Map<String, Object> getLock(String name) throws Exception {
        RLock lock = redissonClient.getLock(name+1);
        boolean b = lock.tryLock();
        System.out.println(name+" = " + b);
        return new HashMap<>() {{
            put("result",b);
        }};
    }

    @GetMapping("/unLock")
    public Map<String, Object> unLock(String name) throws IOException, ExecutionException, InterruptedException {
        Future<Boolean> submit = singleThreadEventExecutor.submit(() -> {
            RLock lock = redissonClient.getLock(name);
            lock.unlock();
            return true;
        });


        return new HashMap<>() {{
            put("result", submit.get());
        }};
    }


    @GetMapping("/getLimiter")
    public Map<String, Object> getLimter(String name) throws IOException, ExecutionException, InterruptedException {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(name);
        rateLimiter.setRate(RateType.PER_CLIENT,10,10,RateIntervalUnit.MINUTES);
        //rateLimiter.acquire(1);
        return new HashMap<>() {{put("result", "ok");}};
    }

    @GetMapping("/limitacquire")
    public Map<String, Object> limitAcquire(String name) throws IOException, ExecutionException, InterruptedException {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(name);
        rateLimiter.acquire(1);
        return new HashMap<>() {{put("result", "ok");}};
    }




}
