package top.boking.springbootjetcachemvc.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author Hollis
 */
@Configuration
@EnableMethodCache(basePackages = "top.boking.springbootjetcachemvc")
public class CacheConfiguration {
    /*
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redisson() {
        Config config = new Config();
        *//*
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:7000");
        *//*
        config.useClusterServers()
                // 添加多个集群节点地址，至少要添加一个主节点地址
                .addNodeAddress("redis://127.0.0.1:7000",
                        "redis://127.0.0.1:7001",
                        "redis://127.0.0.1:7002");
        return Redisson.create(config);
    }
    */
}
