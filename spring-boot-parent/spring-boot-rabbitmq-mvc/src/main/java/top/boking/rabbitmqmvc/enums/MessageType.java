package top.boking.rabbitmqmvc.enums;

import java.util.List;
import java.util.Map;

/**
 * 消息类型枚举
 */
public enum MessageType {
    STRING(String.class),
    LIST(List.class),
    MAP(Map.class),
    ;

    private Class clazz;

    MessageType(Class clazz) {
        this.clazz = clazz;
    }
}
