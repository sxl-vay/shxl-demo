package top.boking.rocketmq.normal.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.rocketmq.normal.consumer.MessageConsumer;
import top.boking.rocketmq.normal.producer.MessageProducer;

@RestController
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;
    @Resource
    private MessageConsumer messageConsumer;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message
            , @RequestParam(required = false) Integer delay
            , @RequestParam(required = false) String order
            , @RequestParam(required = false, defaultValue = "test-topic") String topic
            , @RequestParam(required = false, defaultValue = "test-tag") String tag
    ) {
        messageProducer.sendMessage(topic, delay, message, order, tag);
        return "消息发送成功";
    }

    @GetMapping("/receive")
    public String receiveMessage(@RequestParam String topic
            , @RequestParam(required = false) String tag
            , @RequestParam(required = false, defaultValue = "1") Integer batch
            , @RequestParam(required = false) String group
    ) {
        messageConsumer.consume(topic, tag, batch, group);
        return "消息拉取开始";
    }

}