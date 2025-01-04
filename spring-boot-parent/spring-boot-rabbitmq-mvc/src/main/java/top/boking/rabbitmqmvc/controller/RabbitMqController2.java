package top.boking.rabbitmqmvc.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.boking.rabbitmqmvc.core.ConsumerCore;
import top.boking.rabbitmqmvc.entity.ChannelObj;
import top.boking.rabbitmqmvc.newcore.ChannelHolder;
import top.boking.rabbitmqmvc.request.SentMsgRequest;
import top.boking.rabbitmqmvc.util.AMQPUtils;
import top.boking.rabbitmqmvc.util.MessageHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        //开启发布确认模式 （如果不开启这个模式，那么队列发送后会自动确认  newChannel.addConfirmListener 将不会有回调）
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
        //标准的持久化消息参数 deliveryMode = 2
        AMQP.BasicProperties persistentTextPlain = MessageProperties.PERSISTENT_TEXT_PLAIN;
        AMQP.BasicProperties basicProperties = builder.build();
        String channelName = ex + "_send4ex";
        Channel newChannel = channelHolder.getChannel(channelName);
        //开启发布确认模式
        newChannel.confirmSelect();
        newChannel.clearConfirmListeners();
        newChannel.addConfirmListener((sequenceNumber, multiple) -> {
            log.info("confirmed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is confirmed
        }, (sequenceNumber, multiple) -> {
            log.info("nack-ed:sequenceNumber:{} -- multiple:{}",sequenceNumber,multiple);
            // code when message is nack-ed
        });
        //启动事务消息，效率低
        //newChannel.txSelect();
        for (Integer i = 0; i < request.getMultiple(); i++) {
            String msg = MessageHelper.buildMsg(request, i);
            newChannel.basicPublish(ex, request.getRoutingKey(), basicProperties, msg.getBytes());
        }
        // 确保消息写入磁盘
        //newChannel.txCommit();

        //等待所有的消息都被接受到（并不代表着所有的消息都被持久化了；也就是意味着如果mq崩溃，消息依旧可能会消失）
        boolean b = newChannel.waitForConfirms();
        return "ok"+b;
    }



    @GetMapping("/queue")
    public Map<String, Object> queue(String name) throws IOException {
        Channel channel = channelHolder.getChannel("queueDeclare");
        channel.queueDeclare(name, true, false, false, null);
        return new HashMap<>() {{
            put("name", name);
        }};
    }

    @GetMapping("/unListen")
    public Map<String, Object> unListen(String queue, String listener) throws IOException {
        String listenChannelName = AMQPUtils.getListenChannelName(queue, listener);
        ChannelObj channelObj = channelHolder.getChannelObj(listenChannelName);
        Channel channel = channelObj.getChannel();
        channel.basicCancel(channelObj.getConsumerTag());
        return new HashMap<>();
    }
}
