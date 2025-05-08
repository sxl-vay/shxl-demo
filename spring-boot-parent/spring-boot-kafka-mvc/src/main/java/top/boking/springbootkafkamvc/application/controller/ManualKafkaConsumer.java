package top.boking.springbootkafkamvc.application.controller;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
public class ManualKafkaConsumer {
    
    private final KafkaConsumer<String, String> consumer;
    
    public ManualKafkaConsumer(ConsumerFactory<String, String> consumerFactory) {
        this.consumer = (KafkaConsumer<String, String>) consumerFactory.createConsumer();
        this.consumer.subscribe(Collections.singletonList("my_topic")); // 订阅主题
    }

    //每8秒拉取一次消息
    @Scheduled(fixedRate = 8000)
    public void pollMessages() {
        System.out.println("Polling for messages...");
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        records.forEach(record -> {
            System.out.printf("Received message: key=%s, value=%s, partition=%d, offset=%d%n",
                record.key(), record.value(), record.partition(), record.offset());
        });
        consumer.commitSync(); // 手动提交offset
    }
    
    @PreDestroy
    public void close() {
        consumer.close();
    }
}