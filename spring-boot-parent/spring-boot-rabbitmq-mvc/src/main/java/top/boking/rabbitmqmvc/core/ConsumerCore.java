package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author shxl
 * @Date 2024/9/26 22:04
 * @Version 1.0
 */
public class ConsumerCore {
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


    public static void consumer(String queue){
        EXECUTOR.submit(() -> listenToQueue(RabbitCore.getNewChannel(), queue));
    }

    private static void listenToQueue(Channel channel, String queueName) {
        try {
            try {
                RabbitCore.getNewChannel().queueDeclare(queueName, true, false, false, null);
            } catch (Exception e) {
                System.out.println("队列已存在："+queueName);
            }

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("Received from " + queueName + ": " + message);
                    channel.basicAck(envelope.getDeliveryTag(), false);  // 手动确认消息
                }
            };
            channel.basicConsume(queueName, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
