package top.boking.rabbitmqmvc.util;

public class AMQPUtils {
    public static String getListenChannelName(String queue, String listener) {
        String channelName;
        if (listener == null || listener.isEmpty()) {
            channelName = queue +"::consumer";
        } else {
            channelName = queue +"listener:"+ listener + " consumer";
        }
        return channelName;
    }
}
