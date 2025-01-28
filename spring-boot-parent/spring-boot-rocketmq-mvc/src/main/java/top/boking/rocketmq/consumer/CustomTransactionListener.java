package top.boking.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RocketMQTransactionListener
@Component
@Slf4j
public class CustomTransactionListener implements RocketMQLocalTransactionListener {
    
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            // 执行本地事务
            String transactionId = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID, String.class);
            String body = new String((byte[]) msg.getPayload());
            log.info("执行本地事务，事务ID: {}, 消息内容: {}", transactionId, body);
            
            // 这里执行您的业务逻辑
            // 比如数据库操作等
            
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("本地事务执行失败", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 回查本地事务状态
        String transactionId = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID, String.class);
        log.info("回查本地事务状态，事务ID: {}", transactionId);
        
        // 这里检查本地事务状态
        // 根据实际情况返回：COMMIT、ROLLBACK 或 UNKNOWN
        return RocketMQLocalTransactionState.COMMIT;
    }
}