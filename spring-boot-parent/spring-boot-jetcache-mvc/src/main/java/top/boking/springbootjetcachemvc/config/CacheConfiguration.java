package top.boking.springbootjetcachemvc.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author Hollis
 */
@Configuration
@EnableMethodCache(basePackages = "top.boking.springbootjetcachemvc")
public class CacheConfiguration {
}
