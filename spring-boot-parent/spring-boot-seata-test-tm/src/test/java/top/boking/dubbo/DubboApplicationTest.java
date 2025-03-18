package top.boking.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import top.boking.seata.tcc.TccActionOne;
import top.boking.springbootseatatesttm.service.LocalTccService;

public class DubboApplicationTest {
    public static void main(String[] args) {
        //构造本地dubbo服务配置信息
        ServiceConfig<LocalTccService> serviceConfig = getConfig();
        //建造造dubbo服务
        DubboBootstrap instance = DubboBootstrap.getInstance();
        instance.application(new ApplicationConfig("dubbo-application"))
                .registry(new RegistryConfig("nacos://192.168.42.234:8848"))
                .protocol(new ProtocolConfig("dubbo", 20880))
                .service(serviceConfig)
                .start()
                .await();
    }

    private static ServiceConfig<LocalTccService> getConfig() {
        ServiceConfig<LocalTccService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(TccActionOne.class);
        serviceConfig.setRef(new LocalTccService());
        return serviceConfig;
    }
}
