package top.boking.rocketmq.transaction.constomer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import top.boking.rocketmq.consts.MQConst;

@Component
@RocketMQMessageListener(
        topic = (MQConst.TRANSATION_TOPIC),
        consumerGroup = (MQConst.TRANSATION_GROUP)
)
@Slf4j
public class ComsumeraListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String event) {
        log.info("事务消息消费 message:{}", event);
    }
}


