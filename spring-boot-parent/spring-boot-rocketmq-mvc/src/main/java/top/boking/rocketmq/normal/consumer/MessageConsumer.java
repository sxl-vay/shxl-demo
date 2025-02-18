package top.boking.rocketmq.normal.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class MessageConsumer {
    @Value("${rocketmq.name-server}")
    private String nameServer;
    private final Map<String, DefaultLitePullConsumer> consumerMap = new ConcurrentHashMap<>();


    public void consume(String topic, String tag, Integer batch, String group){
        // 获取或创建消费者实例
        DefaultLitePullConsumer consumer = consumerMap.computeIfAbsent(group, k -> {
            try {
                DefaultLitePullConsumer newConsumer = new DefaultLitePullConsumer(group);
                newConsumer.setNamesrvAddr(nameServer);
                newConsumer.setPullBatchSize(batch);
                newConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET); // 从最新位置开始消费
                newConsumer.subscribe(topic, tag);
                newConsumer.start();
                return newConsumer;
            } catch (MQClientException e) {
                log.error("创建消费者失败", e);
                throw new RuntimeException(e);
            }
        });

        // 拉取消息，超时时间为 5 秒
        List<MessageExt> messages = consumer.poll(5000);
        if (CollectionUtils.isEmpty(messages)) {
            log.warn("empty::msg");
            return;
        }

        // 处理消息
        for (MessageExt message : messages) {
            try {
                String tags = message.getTags();
                String body = new String(message.getBody(), StandardCharsets.UTF_8);
                String maxOffset = message.getProperties().get("MAX_OFFSET");
                log.info("\n收到消息 - Topic: {}, Tags: {}, Body: {}, maxOffSet:{}", topic, tags, body, maxOffset);
                // 在这里处理你的业务逻辑
            } catch (Exception e) {
                log.error("处理消息失败", e);
            }
        }
    }
}
