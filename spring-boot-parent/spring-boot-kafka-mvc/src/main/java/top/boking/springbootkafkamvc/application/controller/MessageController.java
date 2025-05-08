package top.boking.springbootkafkamvc.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.boking.springbootkafkamvc.infrastructure.producer.KafkaProducerService;

@RestController
public class MessageController {
    
    private final KafkaProducerService producerService;
    
    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }
    
    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return "Message sent: " + message;
    }
}