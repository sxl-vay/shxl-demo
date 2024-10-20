package top.boking.redismvc.controller;

import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

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

    @Autowired
    private Jedis jedis;

    @Autowired
    private JedisCluster jedisCluster;

    @GetMapping("/t1")
    public Map<String, Object> queue(String name) throws IOException {
        RSet<Object> set1 = redissonClient.getSet(name);
        set1.add("seta");
        set1.add("setb");
        return new HashMap<>(){{put("result",set1);}};
    }
}
