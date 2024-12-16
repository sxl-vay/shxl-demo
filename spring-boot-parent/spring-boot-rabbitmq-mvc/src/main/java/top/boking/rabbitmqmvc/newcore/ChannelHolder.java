package top.boking.rabbitmqmvc.newcore;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class ChannelHolder {
    @Autowired
    private ConnectionFactoryHolder connectionFactoryHolder;

    private final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 根据队列的唯一标识获取队列
     */
    public Channel getChannel(String channelName) {
        if (channelMap.containsKey(channelName)) {
            return channelMap.get(channelName);
        }
        Channel channel = getNewChannel();
        channelMap.put(channelName, channel);
        return channel;
    }

    public Channel getNewChannel() {
        Connection connection = connectionFactoryHolder.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return channel;
    }

    public void closeChannel(String channelName) {
        Channel channel = channelMap.get(channelName);
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                log.error(e.getMessage(), e);
            }
            channelMap.remove(channelName);
        }
    }

}
