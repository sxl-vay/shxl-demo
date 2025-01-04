package top.boking.rabbitmqmvc.request;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import top.boking.rabbitmqmvc.enums.MessageType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class SentMsgRequest implements Serializable {
    /**
     * 消息内容
     */
    private Object msg;
    /**
     * 交换机名称
     */
    private String exchange;
    /**
     * 路由键（没有交换机切有路由键的情况下会被忽略）
     */
    private String routingKey;
    /**
     * 队列名称（有交换机的情况下会被忽略）
     */
    private String queue;
    /**
     * 过期时间（sdk默认的单位为毫秒，且默认为string不是数字类型）
     */
    private String ttl;
    /**
     * 单条消息需要被扩大的次数，默认为1
     * 被扩大后的消息内容都会增加一个 前缀
     */
    private Integer multiple;

    public Integer getMultiple() {
        boolean empyt = isEmpyt(multiple);
        if (empyt || multiple < 1) {
            return 1;
        }
        return multiple;
    }

    public String getExchange() {
        if (isEmpyt(exchange)) {
            return "";
        }
        return exchange;
    }

    public String getRoutingKey() {
        if (isEmpyt(queue)) {
            return routingKey;
        }
        return queue;
    }

    public String getQueue() {
        return queue;
    }

    public String prefix(int i) {
        if (multiple!= null &&multiple > 1) {
            return "multiple msg prefix:"+i;
        }
        return "";
    }

    public String getMsgStr() {
        if (msg instanceof String) {
            return (String) msg;
        } else if (msg instanceof List || msg instanceof Map) {
            return JSONObject.toJSONString(msg);
        }
        return msg.toString();
    }

    public MessageType getMessageType() {
        if (msg instanceof String) {
            return MessageType.STRING;
        } else if (msg instanceof List) {
            return MessageType.LIST;
        } else if (msg instanceof Map) {
            return MessageType.MAP;
        }
        return null;
    }

    /**
     * 请求信息是否有效
     * @return
     */
    public boolean validVerify() {
        boolean invalid = isEmpyt(exchange) && isEmpyt(routingKey) && isEmpyt(queue);
        return !invalid;

    }
    private boolean isEmpyt(Object str) {
        return str == null || "".equals(str);
    }


}
