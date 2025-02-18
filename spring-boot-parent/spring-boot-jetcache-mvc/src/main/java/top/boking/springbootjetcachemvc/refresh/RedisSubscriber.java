package top.boking.springbootjetcachemvc.refresh;

import jakarta.annotation.PostConstruct;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;


@Component
public class RedisSubscriber {
    private final RTopic topic;
    private final JetCacheUtil cacheUtil;

    public RedisSubscriber(RedissonClient redissonClient, JetCacheUtil cacheUtil) {
        this.topic = redissonClient.getTopic("cache_invalidate");
        this.cacheUtil = cacheUtil;
    }

    @PostConstruct
    public void subscribe() {
        topic.addListener(String.class, (channel, key) -> {
            System.out.println("[Redisson] 收到缓存失效通知 key=" + key);
            cacheUtil.invalidateCache("userCache",key);
        });
    }
}
