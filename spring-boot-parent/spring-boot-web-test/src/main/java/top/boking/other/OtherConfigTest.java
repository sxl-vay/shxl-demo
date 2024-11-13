package top.boking.other;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherConfigTest {

    @Data
    public static class OtherConfig {
        private String name;
    }
    @Bean
    public OtherConfig otherConfig() {
        OtherConfig otherConfig = new OtherConfig();
        otherConfig.setName("shxl:OtherConfig");
        return otherConfig;
    }
}
