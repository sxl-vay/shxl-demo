package top.boking.springbootkafkamvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootKafkaMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaMvcApplication.class, args);
    }

}
