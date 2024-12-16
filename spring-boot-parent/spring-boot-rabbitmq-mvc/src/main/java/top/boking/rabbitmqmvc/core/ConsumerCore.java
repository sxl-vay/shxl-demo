package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.boking.rabbitmqmvc.newcore.ChannelHolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author shxl
 * @Date 2024/9/26 22:04
 * @Version 1.0
 */
@Component
@Slf4j
public class ConsumerCore {
    @Autowired
    private ChannelHolder channelHolder;

    public enum ConsumerType {
        none,
        stream
    }

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
            2,
            2,
            1,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100)
            , s -> {
        Thread thread = new Thread(s);
        thread.setName("shxl-queue-thread");
        return thread;
    }
            , new ThreadPoolExecutor.CallerRunsPolicy()
    );


    public void consumer(String queue, ConsumerType type, String listener) {
        String channelName;
        if (listener == null || listener == "") {
            channelName = queue+"::consumer";
        } else {
            channelName = queue +"listener:"+ listener + " consumer";
        }
        Channel channel = channelHolder.getChannel(channelName);

        switch (type) {
            case none -> listenToQueue(channel, queue, channelName);
            case stream -> listenToStream(channel, queue);
        }
    }

    /**
     * 消费消息
     * @param channel
     * @param queueName
     * @param channelName
     */
    private static void listenToQueue(Channel channel, String queueName, String channelName) {
        try {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    long deliveryTag = envelope.getDeliveryTag();//当前的传递标签，（对于同一个队列，不同的消费者，即不同信道，有单独的deliveryTag）
                    log.info("Received from channelName:{},deliveryTag:{},msg:{}", channelName, deliveryTag, message);
                    //仅针对指定的消息进行确认，没有确认的消息会进入unacked状态，超时进入死信队列
                    /*
                    if (channelName.contains("has_deal_queue_1212listener:2")) {
                        channel.basicAck(deliveryTag, true);
                    }
                    */
                    channel.basicAck(deliveryTag, true);//确认消息
                    //拒绝消息  requeue 代表重复投递此消息
                    channel.basicNack(envelope.getDeliveryTag(), false, false);
                }
            };
            //autoAck设置为false 则代表消息需要手动确认；
            channel.basicConsume(queueName, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenToStream(Channel channel, String streamName) {
        try {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    log.info("Received from " + streamName + ": " + message);
                    channel.basicAck(envelope.getDeliveryTag(), false);  // 手动确认消息
                }
            };
            channel.basicQos(1); // QoS must be specified
            channel.basicConsume(
                    streamName,
                    false,
                    Collections.singletonMap("x-stream-offset", 1), // "first" offset specification
                    (consumerTag, message) -> {
                        byte[] body = message.getBody();
                        String messageStr = new String(body, StandardCharsets.UTF_8);
                        log.info("messageStr = " + messageStr);
                        // message processing
                        // ...
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false); // ack is required
                    },
                    consumerTag -> {
                        log.info("listenToStream:consumerTag:" + consumerTag);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
