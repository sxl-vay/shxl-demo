package top.boking.rabbitmq.test;

import com.rabbitmq.client.*;

/**
 * @Author shxl
 * @Date 2024/9/25 22:42
 * @Version 1.0
 */
public class RabbitMqTest {
    private static final String DEAD_QUEUE = "deal-letter-queue";
    private static final String MAIN_QUEUE = "deal-letter-queue";
    private static final String DEAD_EXCHANGE = "deal-letter-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(DEAD_EXCHANGE, false, false, true, null);
            channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"deal-letter-exchange");
            channel.queueDeclare(MAIN_QUEUE, false, false, true, null);

        }
    }
}
