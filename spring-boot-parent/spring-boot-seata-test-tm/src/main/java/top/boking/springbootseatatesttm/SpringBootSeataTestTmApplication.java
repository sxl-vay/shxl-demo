package top.boking.springbootseatatesttm;

import io.seata.config.springcloud.EnableSeataSpringConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SpringBootSeataTestTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSeataTestTmApplication.class, args);
    }

}
