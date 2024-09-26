package top.boking.rabbitmqmvc.core;

/**
 * @Author shxl
 * @Date 2024/9/26 21:22
 * @Version 1.0
 */
public class RabbitMqConsts {
    public static final String EXCHANGE_DIRECT = "ex_direct";
    public static final String EXCHANGE_FANOUT = "ex_fanout";
    public static final String EXCHANGE_TOPIC = "ex_topic";

    public static final String NORMAL_DEAD_EXCHANGE = "deal_ex_normal";
    public static final String NORMAL_DEAL_QUEUE = "deal_q_normal";
    public static final String NORMAL_DEAL_KEY = "deal_k_normal";
}
