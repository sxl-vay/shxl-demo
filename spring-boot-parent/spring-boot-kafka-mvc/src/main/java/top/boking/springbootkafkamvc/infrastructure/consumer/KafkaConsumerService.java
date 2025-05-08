package top.boking.springbootkafkamvc.infrastructure.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    
    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
    
    // 可以监听多个主题
    @KafkaListener(topics = {"topic1", "topic2"})
    public void consumeMultipleTopics(String message) {
        System.out.println("Received from multiple topics: " + message);
    }
}