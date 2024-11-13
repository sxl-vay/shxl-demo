package top.boking.webtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shxl
 * @Date 2024/9/19 22:08
 * @Version 1.0
 */
@Configuration
public class TestStartConfig {
    private String test;

    public String getTest() {
        return test;
    }

    public TestStartConfig() {
        System.out.println("shxl:::TestStartConfig");
    }

    @Bean("test")
    public TestStartConfig testStartConfig() {
        TestStartConfig testStartConfig = new TestStartConfig();
        testStartConfig.test = "init test";
        return testStartConfig;

    }
}
