package top.boking.rabbitmqmvc.entity;

import com.rabbitmq.client.Channel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChannelObj {
    private Channel channel;
    private String queue;
    private String consumerTag;
}
