package top.boking.rocketmq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }
    
    public void sendMessageWithTag(String topic, String tag, String message) {
        rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
    }
    // 在 MessageProducer 中添加
    public void sendOrderlyMessage(String topic, String message, String hashKey) {
        rocketMQTemplate.syncSendOrderly(topic, message, hashKey);
    }
    // 在 MessageProducer 中添加
    public void sendDelayMessage(String topic, String message, int delayLevel) {
        Message<String> msg = MessageBuilder.withPayload(message).build();
        rocketMQTemplate.syncSend(topic, msg, 3000, delayLevel);
    }
}