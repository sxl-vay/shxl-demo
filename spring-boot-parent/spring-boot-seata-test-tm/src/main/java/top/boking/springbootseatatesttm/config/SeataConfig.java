package top.boking.springbootseatatesttm.config;

import com.alibaba.druid.util.StringUtils;
import io.seata.spring.annotation.GlobalTransactionScanner;
import io.seata.spring.boot.autoconfigure.properties.SeataProperties;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shxl
 * @Date 2024/10/25 22:23
 * @Version 1.0
 */
@Configuration
public class SeataConfig {
    /*
    @Primary
    @Bean("dataSource")
    public DataSource dataSource(DataSource druidDataSource) {
        //AT 代理 二选一
        return new DataSourceProxy(druidDataSource);
    }
    */

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private SeataProperties seataProperties;

    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        String txServiceGroup = this.seataProperties.getTxServiceGroup();
        if (StringUtils.isEmpty(txServiceGroup)) {
            txServiceGroup = applicationName + "-fescar-service-group";
            this.seataProperties.setTxServiceGroup(txServiceGroup);
        }

        return new GlobalTransactionScanner(applicationName, txServiceGroup);
    }


}
