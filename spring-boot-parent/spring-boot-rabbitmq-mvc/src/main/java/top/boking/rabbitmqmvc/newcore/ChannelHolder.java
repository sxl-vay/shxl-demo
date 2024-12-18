package top.boking.rabbitmqmvc.newcore;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.boking.rabbitmqmvc.entity.ChannelObj;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class ChannelHolder {
    @Autowired
    private ConnectionFactoryHolder connectionFactoryHolder;

    private final Map<String, ChannelObj> channelMap = new ConcurrentHashMap<>();

    /**
     * 根据队列的唯一标识获取队列
     */
    public Channel getChannel(String channelName) {
        if (channelMap.containsKey(channelName)) {
            return channelMap.get(channelName).getChannel();
        }
        Channel channel = getNewChannel();
        channelMap.put(channelName, ChannelObj.builder().channel(channel).build());
        return channel;
    }
    public ChannelObj getChannelObj(String channelName) {
        if (channelMap.containsKey(channelName)) {
            return channelMap.get(channelName);
        }
        Channel channel = getNewChannel();
        channelMap.put(channelName, ChannelObj.builder().channel(channel).build());
        return channelMap.get(channelName);
    }
    public ChannelObj refreshChannelObj(String channelName,ChannelObj refreshChannelObj) {
        ChannelObj channelObj = Optional.ofNullable(channelMap.get(channelName)).orElse(refreshChannelObj);
        BeanUtils.copyProperties(refreshChannelObj, channelObj);
        channelMap.put(channelName, channelObj);
        return channelObj;
    }

    public void closeChannel(String channelName) {
        ChannelObj channel = channelMap.get(channelName);
        if (channel != null) {
            try {
                channel.getChannel().close();
            } catch (IOException | TimeoutException e) {
                log.error(e.getMessage(), e);
            }
            channelMap.remove(channelName);
        }
    }

    private Channel getNewChannel() {
        Connection connection = connectionFactoryHolder.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return channel;
    }

}
