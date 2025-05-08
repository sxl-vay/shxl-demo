package top.boking.springbootkafkamvc.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicTest {
    @Bean
    public NewTopic myTopic() {
        NewTopic myTopic = TopicBuilder.name("my_topic").partitions(1).replicas(1).build();
        System.out.println("myTopic = " + myTopic);
        return myTopic;
    }
}
