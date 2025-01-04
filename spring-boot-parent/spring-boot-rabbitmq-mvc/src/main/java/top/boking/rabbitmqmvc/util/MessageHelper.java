package top.boking.rabbitmqmvc.util;

import org.springframework.amqp.core.Message;
import top.boking.rabbitmqmvc.entity.MessageObj;
import top.boking.rabbitmqmvc.enums.MessageType;
import top.boking.rabbitmqmvc.request.SentMsgRequest;

public class MessageHelper {
    public static final String MESSAGE_DELIMITER = "delimiter";

    public static String buildMsg(SentMsgRequest request, Integer i) {
        return request.getMessageType() + MESSAGE_DELIMITER + i + MessageHelper.MESSAGE_DELIMITER + request.getMsgStr();
    }
    public static MessageObj getMessageObj(Message message) {
        MessageObj obj = new MessageObj();
        try {
            String msgStr = new String(message.getBody());
            String[] segment = msgStr.split(MESSAGE_DELIMITER, 4);
            obj.setMessageType(MessageType.valueOf(segment[0]));
            obj.setOrder(Integer.parseInt(segment[1]));
            obj.setMsgObjStr(segment[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
