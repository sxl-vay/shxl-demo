package top.boking.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class DeadLetterConsumer {

    private static final String DLX_QUEUE = "dlx_queue"; // 死信队列名称

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            // 声明要监听的死信队列
            channel.queueDeclare(DLX_QUEUE, true, false, false, null);

            // 创建消费者接收消息
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Received Dead Letter: " + message);
                
                // 手动确认消息
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            // 监听死信队列，开始接收消息
            channel.basicConsume(DLX_QUEUE, false, deliverCallback, consumerTag -> { });
            System.out.println("Waiting for dead letter messages...");

            // 持续运行程序以监听消息
            while (true) {
                // 保持程序运行，不让其退出
            }
        }
    }
}
