package top.boking.rocketmq.transaction.productor;


import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.rocketmq.consts.MQConst;

@RestController("/rocketmq")
public class TransactionMessageController {

    private static final Logger log = LoggerFactory.getLogger(TransactionMessageController.class);
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping(value = "/transaction")
    public String transaction(@RequestParam(required = false, defaultValue = "1000") String t) {
        Message<String> message = new Message<>() {
            @Override
            public String getPayload() {
                return t;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
        SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(MQConst.TRANSATION_TOPIC, message, null);
        //打印消息内容日志
        log.info("事务消息发送结果: {}", sendResult.getSendStatus());
        SendStatus sendStatus = sendResult.getSendStatus();
        return String.valueOf(sendStatus);
    }
}

