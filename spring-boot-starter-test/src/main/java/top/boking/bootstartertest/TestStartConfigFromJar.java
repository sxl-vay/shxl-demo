package top.boking.bootstartertest;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;

/**
 * @Author shxl
 * @Date 2024/9/19 22:08
 * @Version 1.0
 */
@Data
public class TestStartConfigFromJar implements BeanNameAware {
    private String test;

    public String getTest() {
        return test;
    }

    public TestStartConfigFromJar() {
        System.out.println("shxl:::TestStartConfigFromJar");
    }

    @Bean
    public TestStartConfigFromJar ttt() {
        TestStartConfigFromJar testStartConfigFromJar = new TestStartConfigFromJar();
        testStartConfigFromJar.test = "init test";
        return testStartConfigFromJar;

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("shxl:name:::"+name);
    }
}
