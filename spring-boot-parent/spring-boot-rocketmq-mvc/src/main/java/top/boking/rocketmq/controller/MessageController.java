package top.boking.rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.rocketmq.producer.MessageProducer;

@RestController
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message, @RequestParam(required = false) Integer delay, @RequestParam(required = false) String order) {
        if (delay != null && delay > 0) {
            messageProducer.sendDelayMessage("test-topic", message, delay);
            return "消息发送成功";

        }
        if (order != null && !order.isEmpty()) {
            messageProducer.sendOrderlyMessage("test-topic", message, order);
            return "消息发送成功";

        }
        messageProducer.sendMessage("test-topic", message);

        return "消息发送成功";
    }
}