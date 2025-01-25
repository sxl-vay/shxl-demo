package top.boking.rabbitmq.test;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RabbitMQDLXExample {

    private static final String NORMAL_EXCHANGE = "normal_exchange";
    private static final String DLX_EXCHANGE = "dlx_exchange";
    private static final String NORMAL_QUEUE = "normal_queue";
    private static final String DLX_QUEUE = "dlx_queue";
    private static final String ROUTING_KEY = "normal_routing_key";
    private static final String DLX_ROUTING_KEY = "dlx_routing_key";

    public static void main(String[] args) throws Exception {
        // 创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // 1. 声明普通交换机
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

            // 2. 声明死信交换机
            channel.exchangeDeclare(DLX_EXCHANGE, BuiltinExchangeType.FANOUT);

            // 3. 声明普通队列，并设置其死信交换机及路由键
            channel.queueDeclare(NORMAL_QUEUE, true, false, false, Map.of(
                    "x-dead-letter-exchange", DLX_EXCHANGE, // 设置死信交换机
                    "x-dead-letter-routing-key", DLX_ROUTING_KEY // 设置死信路由键
            ));

            // 4. 声明死信队列
            channel.queueDeclare(DLX_QUEUE, true, false, false, null);
            channel.queueDeclare("DLX_QUEUE2", true, false, false, null);

            // 5. 绑定死信交换机到死信队列
            channel.queueBind(DLX_QUEUE, DLX_EXCHANGE, DLX_ROUTING_KEY);
            channel.queueBind("DLX_QUEUE2", DLX_EXCHANGE, DLX_ROUTING_KEY);

            // 6. 绑定普通交换机到普通队列
            channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, ROUTING_KEY);

            // 7. 向普通队列发送消息
            String message = "shxl test sss";
            AMQP.BasicProperties.Builder propertiesBuilder = new AMQP.BasicProperties.Builder();
            AMQP.BasicProperties basicProperties = propertiesBuilder
                    .expiration("2000")
                    .build();

            channel.basicPublish(NORMAL_EXCHANGE, ROUTING_KEY, basicProperties, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent: " + message);
        } catch (Exception e) {

        }
    }
}
