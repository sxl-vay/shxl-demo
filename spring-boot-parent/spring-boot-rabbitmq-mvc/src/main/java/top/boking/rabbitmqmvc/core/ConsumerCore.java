package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.*;

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
public class ConsumerCore {

    public static enum ConsumerType {
        none,
        stream
    }

    private static final Channel CHANNEL = RabbitCore.getChannel();
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

    public static void consumer(String queue, ConsumerType type) {
        switch (type) {
            case none -> EXECUTOR.submit(() -> listenToQueue(RabbitCore.getNewChannel(), queue));
            case stream -> EXECUTOR.submit(() -> listenToStream(RabbitCore.getNewChannel(), queue));
        }
    }

    public static void consumer1(String queue, ConsumerType type) {
        switch (type) {
            case none -> listenToQueue(RabbitCore.getNewChannel(), queue);
            case stream -> listenToStream(RabbitCore.getNewChannel(), queue);
        }
    }

    private static void listenToQueue(Channel channel, String queueName) {
        try {
            try {
                RabbitCore.getNewChannel().queueDeclare(queueName, true, false, false, null);
            } catch (Exception e) {
                System.out.println("队列已存在：" + queueName);
            }

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Received from " + queueName + ": " + message);
                    channel.basicNack(envelope.getDeliveryTag(), false,false);  // 手动确认消息
                }
            };
            channel.basicConsume(queueName, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenToStream(Channel channel, String streamName) {
        try {
            try {
                RabbitCore.getNewChannel().queueDeclare(streamName, true, false, false, null);
            } catch (Exception e) {
                System.out.println("队列已存在：" + streamName);
            }

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Received from " + streamName + ": " + message);
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
                        System.out.println("messageStr = " + messageStr);
                        // message processing
                        // ...
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false); // ack is required
                    },
                    consumerTag -> {
                        System.out.println("listenToStream:consumerTag:"+consumerTag);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
