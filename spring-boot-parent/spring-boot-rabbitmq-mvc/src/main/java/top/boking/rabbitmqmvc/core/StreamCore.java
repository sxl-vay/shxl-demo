package top.boking.rabbitmqmvc.core;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Collections;

/**
 * @Author shxl
 * @Date 2024/9/27 23:25
 * @Version 1.0
 */
public class StreamCore {
    public void consumerStream() throws IOException {
        Channel channel = RabbitCore.getNewChannel();
        channel.basicQos(0); // QoS must be specified
        channel.basicConsume(
                "stream_a",
                false,
                Collections.singletonMap("x-stream-offset", "first"), // "first" offset specification
                (consumerTag, message) -> {
                    // message processing
                    // ...
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false); // ack is required
                },
                consumerTag -> { });
    }
}
