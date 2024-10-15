package top.boking.redismvc.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @GetMapping("/t1")
    public Map<String, Object> queue(String name) throws IOException {
        RLock a = redissonClient.getLock("shxllock");
        try {
            if (a.tryLock(100,10, TimeUnit.SECONDS)) {
                System.out.println("a = " + a);
            }
            a.unlock();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new HashMap<>();
    }
}
