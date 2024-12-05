package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author shxl
 * @Date 2024/9/26 21:09
 * @Version 1.0
 */
@Component
public class RabbitCore {

    @Getter
    private static Channel channel;
    @Value("${spring.rabbitmq.host}")
    private String host;

    private static ConnectionFactory factory = new ConnectionFactory();
    private static Connection connection;
    static {
        try {
            factory.setHost("192.168.0.103" +
                    "");
            factory.setVirtualHost("shxl_host");
            connection = factory.newConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        try {
            channel = connection.createChannel();
        } catch (Exception e) {
            channel = null;
        }
    }

    public static Channel getNewChannel() {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
