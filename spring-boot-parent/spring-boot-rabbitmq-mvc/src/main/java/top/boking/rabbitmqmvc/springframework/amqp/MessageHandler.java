package top.boking.rabbitmqmvc.springframework.amqp;

import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import top.boking.rabbitmqmvc.entity.MessageObj;
import top.boking.rabbitmqmvc.enums.MessageType;
import top.boking.rabbitmqmvc.util.ArgHolder;
import top.boking.rabbitmqmvc.util.MessageHelper;

public class MessageHandler implements ChannelAwareMessageListener {
    public void handleMessage(String message) {
        System.out.println("Received: " + message);
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageObj msg = MessageHelper.getMessageObj(message);
        MessageType messageType = msg.getMessageType();
        if (messageType == MessageType.MAP) {
            JSONObject jsonObject = JSONObject.parseObject(msg.getMsgObjStr());
            Long later = jsonObject.getLong("later");
            long floor = (long) Math.floor(later * ArgHolder.getMultiple());
            if (floor > 0) {
                Thread.sleep(floor);
            }
        }
        /**
         * com.rabbitmq.client.impl.ChannelN#basicAck(long, boolean)
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println(msg);
    }
}
