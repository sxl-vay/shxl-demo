package top.boking.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
    topic = "order-topic2"
        , consumerGroup = "my-consumer-group8083"
//        , consumeMode = ConsumeMode.ORDERLY
)
@Slf4j
public class MessageListener implements RocketMQListener<String> {
    
    @Override
    public void onMessage(String message) {
        log.info("\n收到消息：" + message);
    }
}