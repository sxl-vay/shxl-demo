package top.boking;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

import java.util.concurrent.TimeUnit;

/**
 * @Author shxl
 * @Date 2024/10/20 22:56
 * @Version 1.0
 */
public class CaffeineDemo {
    public static void main(String[] args) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                .maximumSize(10)//按照条目驱逐
                //.maximumWeight(10*1024)//按照权重驱逐
                .expireAfterAccess(10, TimeUnit.SECONDS)//过期自动淘汰
                .evictionListener((key, value, cause) -> {
                    System.out.println(""+key+value+cause);
                })
                .build();
        for (int i = 0; i < 11; i++) {
            cache.put("shxl:"+i,i);
        }
        cache.put("mor","xxx");
        Object shxl1 = cache.get("shxl:1", (k) -> k);
        System.out.println("shxl1 = " + shxl1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
