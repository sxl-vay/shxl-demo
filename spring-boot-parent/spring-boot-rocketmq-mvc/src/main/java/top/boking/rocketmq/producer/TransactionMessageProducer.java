package top.boking.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.boking.rocketmq.consumer.CustomTransactionListener;

import java.util.UUID;

@Service
@Slf4j
public class TransactionMessageProducer {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    @Autowired
    private CustomTransactionListener transactionListener;
    
    public void sendTransactionMessage(String topic, String message) {
        // 创建事务消息发送器
        TransactionMQProducer producer = (TransactionMQProducer) rocketMQTemplate
            .getProducer();
        // 发送事务消息
        try {
            Message<String> msg = MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .build();
                
            TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                topic,
                msg,
                null  // 这里可以传递额外参数给executeLocalTransaction方法
            );
            
            log.info("事务消息发送结果: {}", sendResult.getSendStatus());
        } catch (Exception e) {
            log.error("发送事务消息失败", e);
            throw new RuntimeException("发送事务消息失败", e);
        }
    }
}