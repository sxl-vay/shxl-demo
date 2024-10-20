package top.boking.springbootjetcachemvc.service;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import org.springframework.stereotype.Service;

/**
 * @Author shxl
 * @Date 2024/10/20 23:49
 * @Version 1.0
 */
@Service
public class JetCacheAService {

    //@CacheInvalidate(name = "aService",key = "#aKey.id")


    @Cached(name = "aService",key = "#cacheKey",expire = 1000)
    @CacheRefresh(refresh = 1000)
    public String getCacheKey(String cacheKey) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        System.out.println("shxl:::"+cacheKey);
        return cacheKey+":value";
    }
}
