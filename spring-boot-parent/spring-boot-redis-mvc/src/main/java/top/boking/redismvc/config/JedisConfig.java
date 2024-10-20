package top.boking.redismvc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author shxl
 * @Date 2024/10/20 20:53
 * @Version 1.0
 */
@Configuration
public class JedisConfig {
    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis() {
        return new Jedis(new HostAndPort("127.0.0.1", 7000));
    }

    @Bean
    @ConditionalOnMissingBean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort("127.0.0.1", 7000));
        JedisCluster jedisCluster = new JedisCluster(clusterNodes);
        return jedisCluster;
    }

}
