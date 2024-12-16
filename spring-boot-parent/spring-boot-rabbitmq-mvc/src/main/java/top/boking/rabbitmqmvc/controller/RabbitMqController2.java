package top.boking.rabbitmqmvc.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.recovery.AutorecoveringChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.boking.rabbitmqmvc.core.ConsumerCore;
import top.boking.rabbitmqmvc.newcore.ChannelHolder;
import top.boking.rabbitmqmvc.request.SentMsgRequest;

import java.io.IOException;

@RestController
@RequestMapping("/rabbitmq2")
@Slf4j
public class RabbitMqController2 {
    private ChannelHolder channelHolder;
    private ConsumerCore consumerCore;

    @Autowired
    public void setChannelHolder(ChannelHolder channelHolder) {
        this.channelHolder = channelHolder;
    }

    @Autowired
    public void setConsumerCore(ConsumerCore consumerCore) {
        this.consumerCore = consumerCore;
    }

    /**
     * 声名交换机
     * @param name
     * @param type
     * @return
     * @throws IOException
     */
    @GetMapping("/ex")
    public String ex(String name, @RequestParam(required = false) String type) throws IOException {
        channelHolder.getChannel("exchangeDeclare").exchangeDeclare(name, type);
        return "ok";
    }

    /**
     * 设置绑定
     * @param ex
     * @param queue
     * @param key
     * @return
     * @throws IOException
     */
    @GetMapping("/bind")
    public String bind(String ex, String queue, @RequestParam(required = false, defaultValue = "") String key) throws IOException {
        Channel channel = channelHolder.getChannel("bind");
        AMQP.Queue.BindOk bindOk = channel.queueBind(queue, ex, key);
        return bindOk.toString();
    }


    /**
     * 消费队列中的消息一次
     * @param queue
     * @param type
     * @return
     */
    @GetMapping("/consumer")
    public String consumer(String queue, String type, @RequestParam(required = false) String listener) {
        ConsumerCore.ConsumerType consumerType = (type == null || "".equals(type))? ConsumerCore.ConsumerType.none : ConsumerCore.ConsumerType.valueOf(type);
        consumerCore.consumer(queue, consumerType, listener);
        return "ok";
    }

    /**
     * 向交换机发送消息
     * @param ex
     * @param key
     * @param msg
     * @param ttl
     * @return
     * @throws IOException
     */
    @GetMapping("/send4ex")
    public String send4ex(@RequestParam(required = false, defaultValue = "") String ex, @RequestParam(required = false, defaultValue = "") String key, String msg, String ttl) throws IOException {
        if ("".equals(ex) && "".equals(key)) {
            return "error: ex and key is empty";
        }
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        if (ttl != null && !"".equals(ttl)) {
            builder.expiration(ttl);
        }
        AMQP.BasicProperties basicProperties = builder.build();
        String channelName = ex + "_send4ex";
        Channel newChannel = channelHolder.getChannel(channelName);
        //newChannel.confirmSelect();
        newChannel.addConfirmListener((sequenceNumber, multiple) -> {
            log.info("confirmed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is confirmed
        }, (sequenceNumber, multiple) -> {
            log.info("nack-ed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is nack-ed
        });
        try {
            newChannel.basicPublish(ex, key, basicProperties, msg.getBytes());
        } catch (Exception e) {
            channelHolder.closeChannel(channelName);
        }
        return "ok";
    }

    @PostMapping("/send")
    public String sent(@RequestBody SentMsgRequest request) throws Exception {
        if (!request.validVerify()) {
            return "error:invalid";
        }
        String ex = request.getExchange();
        String ttl = request.getTtl();
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        if (ttl != null && !"".equals(ttl)) {
            builder.expiration(ttl);
        }
        AMQP.BasicProperties basicProperties = builder.build();
        String channelName = ex + "_send4ex";
        AutorecoveringChannel newChannel = (AutorecoveringChannel)channelHolder.getChannel(channelName);
        newChannel.confirmSelect();
        newChannel.clearConfirmListeners();
        newChannel.addConfirmListener((sequenceNumber, multiple) -> {
            log.info("confirmed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is confirmed
        }, (sequenceNumber, multiple) -> {
            log.info("nack-ed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is nack-ed
        });
        for (Integer i = 0; i < request.getMultiple(); i++) {
            String msg ="multiple msg prefix:"+i+":::"+ request.getMsg();
            newChannel.basicPublish(ex, request.getRoutingKey(), basicProperties, msg.getBytes());
        }

        return "ok";
    }
}
