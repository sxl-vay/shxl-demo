package top.boking.springbootjetcachemvc.refresh;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JetCacheUtil {
    private final CacheManager cacheManager;

    public JetCacheUtil(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 清理指定 key 的缓存
     */
    public void invalidateCache(String cacheName, String key) {
        Cache<Object, Object> cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.remove(key);
            System.out.println("[JetCache] 清理本地缓存 cache=" + cacheName + ", key=" + key);
        }
    }

    /**
     * 手动添加缓存（可选）
     */
    public void putCache(String cacheName, String key, Object value, long expireSeconds) {
        Cache<Object, Object> cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value, expireSeconds, TimeUnit.SECONDS);
        }
    }
}
