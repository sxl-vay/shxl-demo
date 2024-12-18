package top.boking.rabbitmqmvc.newcore;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ConnectionFactoryHolder {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.virtual-host:null}")
    private String virtualHost;
    private static Connection connection;
    @Value("${spring.rabbitmq.port}")
    private int port;

    private static boolean initialized = false;
    public Connection getConnection() {
        if (!initialized) {
            synchronized (this) {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost(host);
                if (virtualHost != null && !"null".equals(virtualHost)) {
                    factory.setVirtualHost(virtualHost);
                }
                if (port != 0) {
                    factory.setPort(port);
                }
                try {
                    connection = factory.newConnection();
                    return connection;
                } catch (IOException | TimeoutException e) {
                    throw new RuntimeException(e);
                } finally {
                    initialized = true;
                }
            }

        }
        return connection;
    }


}
