package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
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

    private static ConnectionFactory factory = new ConnectionFactory();
    static {
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (Exception e) {
            channel = null;
        }
    }

    public static Channel getNewChannel() {
        try {
            return factory.newConnection().createChannel();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
