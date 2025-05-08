package top.boking.springbootkafkamvc.infrastructure.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    
    private static final String TOPIC = "my_topic";
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send(TOPIC, message);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Message sent: " + message);
            } else {
                System.out.println("Error sending message: " + ex.getMessage());
            }
        });
        // 或者指定key
        // kafkaTemplate.send(TOPIC, "key", message);
    }
}