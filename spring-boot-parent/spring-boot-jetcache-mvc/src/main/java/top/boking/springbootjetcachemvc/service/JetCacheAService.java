package top.boking.springbootjetcachemvc.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.Cached;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.boking.springbootjetcachemvc.refresh.RedissonPublisher;

/**
 * @Author shxl
 * @Date 2024/10/20 23:49
 * @Version 1.0
 */
@Service
public class JetCacheAService {

    private String v = "init";

    @Resource
    private RedissonPublisher redissonPublisher;

    private static final String CACHE_NAME = "userCache";

    @Cached(name = CACHE_NAME,key = "#cacheKey",expire = 1000)
    @CachePenetrationProtect
    public String getCacheKey(String cacheKey) {
        System.out.println("shxl:::"+cacheKey);
        return cacheKey+":::"+v;
    }

    @CacheInvalidate(name = CACHE_NAME, key = "#cacheKey")
    public void updateUser(String cacheKey, String newValue) {
        System.out.println("[DB] 更新 userId=" + cacheKey + " 为：" + newValue);
        v = newValue;
        // 远程缓存已通过 @CacheInvalidate 失效
        // 额外发布 Redisson 失效通知，通知其他节点清理本地缓存
        redissonPublisher.publishCacheInvalidation(cacheKey);
    }
}
