package top.boking.rabbitmqmvc.entity;

import lombok.Data;
import top.boking.rabbitmqmvc.enums.MessageType;

@Data
public class MessageObj {
    private Integer order;
    private MessageType messageType;
    private String msgObjStr;
}
