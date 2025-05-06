package top.boking.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import java.util.HashMap;

public class ProducerTest {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("group_1");
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.createTopic("topic_1", "my_tag", 3, new HashMap<>());

//        producer.sendOneway();
    }
}
