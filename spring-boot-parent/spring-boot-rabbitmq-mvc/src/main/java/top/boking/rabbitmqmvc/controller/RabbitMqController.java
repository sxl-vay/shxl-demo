package top.boking.rabbitmqmvc.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.rabbitmqmvc.core.ConsumerCore;
import top.boking.rabbitmqmvc.core.RabbitCore;
import top.boking.rabbitmqmvc.core.RabbitMqConsts;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/9/26 21:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    private final Channel channel = RabbitCore.getChannel();

    @GetMapping("/queue")
    public Map<String, Object> queue(String name) throws IOException {
        channel.queueDeclare(name, true, false, false, null);
        return new HashMap<>() {{
            put("name", name);
        }};
    }

    @GetMapping("/queue4dead")
    public Map<String, Object> queue4dead(String name, String dex, String dexType, String key, String dexQueue) throws IOException {
        if (dex == null) {
            dex = RabbitMqConsts.NORMAL_DEAD_EXCHANGE;
        }
        if (dexType == null) {
            dexType = "direct";
        }
        if (dexQueue == null) {
            dexQueue = RabbitMqConsts.NORMAL_DEAL_QUEUE;
        }
        if (key == null) {
            key = RabbitMqConsts.NORMAL_DEAL_KEY;
        }
        try {
            channel.exchangeDeclare(dex, dexType);
        } catch (Exception e) {

        }
        try {
            channel.queueDeclare(dexQueue, true, false, false, null);
        } catch (Exception e) {

        }
        try {
            channel.queueBind(dexQueue, dex, key);
        } catch (Exception e) {

        }


        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", dex); // 设置死信交换机
        arguments.put("x-dead-letter-routing-key", key); // 可选地设置路由键
        Channel newChannel = RabbitCore.getNewChannel();
        newChannel.queueDeclare(name, true, false, false, arguments);
        return new HashMap<>() {{
            put("name", name);
        }};
    }

    @GetMapping("/ex")
    public String ex(String name, @RequestParam(required = false) String type) throws IOException {
        channel.exchangeDeclare(name, type);
        return "ok";
    }

    @GetMapping("/bind")
    public String bind(String ex, String queue, @RequestParam(required = false, defaultValue = "") String key) throws IOException {
        try {
            RabbitCore.getNewChannel().queueDeclare(queue, true, false, false, null);
        } catch (Exception e) {

        }
        RabbitCore.getNewChannel().queueBind(queue, ex, key);
        return "ok";
    }

    @GetMapping("/send4queue")
    public String send4queue(String queue, String msg, String ttl) throws IOException {
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration(ttl).build();
        RabbitCore.getNewChannel().basicPublish("", queue, build, msg.getBytes());
        return "ok";
    }

    /**
     * 带有发布确认功能
     * @param ex
     * @param key
     * @param msg
     * @param ttl
     * @return
     * @throws IOException
     */
    @GetMapping("/send4ex")
    public String send4ex(String ex, @RequestParam(required = false, defaultValue = "") String key, String msg, String ttl) throws IOException {
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration(ttl).build();
        Channel newChannel = RabbitCore.getNewChannel();
        newChannel.confirmSelect();
        newChannel.addConfirmListener((sequenceNumber, multiple) -> {
            System.out.println("conform:confirmed:"+sequenceNumber+multiple);
            // code when message is confirmed
        }, (sequenceNumber, multiple) -> {
            System.out.println("conform: nack-ed:"+sequenceNumber+multiple);
            // code when message is nack-ed
        });
        newChannel.basicPublish(ex, key, build, msg.getBytes());
        return "ok";
    }

    @GetMapping("/consumer")
    public String consumer(String queue, String type) {
        ConsumerCore.ConsumerType consumerType = type == null ? ConsumerCore.ConsumerType.none : ConsumerCore.ConsumerType.valueOf(type);
        ConsumerCore.consumer(queue, consumerType);
        return "ok";
    }

    @GetMapping("/consumer1")
    public String consumer1(String queue, String type) {
        ConsumerCore.ConsumerType consumerType = type == null ? ConsumerCore.ConsumerType.none : ConsumerCore.ConsumerType.valueOf(type);
        ConsumerCore.consumer1(queue, consumerType);
        return "ok";
    }


}
