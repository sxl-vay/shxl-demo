package top.boking.springbootjetcachemvc.refresh;

import jakarta.annotation.Resource;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
public class RedissonPublisher {
    private static final String CHANNEL_NAME = "cache_invalidate";

    @Resource
    private RedissonClient redissonClient;

    public void publishCacheInvalidation(String key) {
        RTopic topic = redissonClient.getTopic(CHANNEL_NAME);
        topic.publish(key);
        System.out.println("[Redisson] 发布缓存失效通知 key=" + key);
    }
}
