package top.boking.rocketmq.normal.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, Integer delayLevel, String message, String order, String tag) {
        log.info("\nseng:mg:{},{},{},{},{}",topic,delayLevel,message,order,tag);
        String destination = topic;
        if (tag != null && !tag.isEmpty()) {
            destination = topic + ":" + tag;
        }
        Message<String> msg = MessageBuilder.withPayload(message)
                .build();
        if (delayLevel != null && delayLevel > 0) {
            rocketMQTemplate.syncSend(destination, msg, 3000, delayLevel);
            return;

        }
        if (order != null && !order.isEmpty()) {
            rocketMQTemplate.syncSendOrderly(destination, message, order);
            return;
        }
        rocketMQTemplate.asyncSend(destination, msg,new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send ok");
            }

            @Override
            public void onException(Throwable e) {
                log.info("send error", e);
            }
        });
    }

    public void sendMessageWithTag(String topic, String tag, String message) {
        rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
    }

    // 在 MessageProducer 中添加
    public void sendOrderlyMessage(String topic, String message, String hashKey) {
        rocketMQTemplate.syncSendOrderly(topic, message, hashKey);
    }

    // 在 MessageProducer 中添加
    public void sendTest(String topic, String message, int delayLevel) {


        Message<String> msg = MessageBuilder.withPayload(message)
                .build();
        rocketMQTemplate.syncSend(topic, msg, 3000, delayLevel);
    }
}