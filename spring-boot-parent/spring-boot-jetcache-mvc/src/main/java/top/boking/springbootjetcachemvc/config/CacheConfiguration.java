package top.boking.springbootjetcachemvc.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${base.host}")
    private String host;
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redisson() {
        Config config = new Config();
        loadSingleServerConfig(config);
        //       loadClusterServerConfig(config);
        return Redisson.create(config);
    }

    private void loadSingleServerConfig(Config config) {
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://192.168.0.103:6379");
    }

    private void loadClusterServerConfig(Config config) {
        config.useClusterServers()
                // 添加多个集群节点地址，至少要添加一个主节点地址
                .addNodeAddress("redis://"+host+":7000",
                        "redis://"+host+":7001",
                        "redis://"+host+":7002");
    }

}
