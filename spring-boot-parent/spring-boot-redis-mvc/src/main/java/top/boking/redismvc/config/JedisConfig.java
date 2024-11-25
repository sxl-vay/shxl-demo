package top.boking.redismvc.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${base.redis.ip}")
    private String ip;

    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis() {
        return new Jedis(new HostAndPort(ip, 6379));
    }

    /*
    @Bean
    @ConditionalOnMissingBean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> clusterNodes = new HashSet<>();
        clusterNodes.add(new HostAndPort(ip, 6379));
        JedisCluster jedisCluster = new JedisCluster(clusterNodes);
        return jedisCluster;
    }
*/
}
