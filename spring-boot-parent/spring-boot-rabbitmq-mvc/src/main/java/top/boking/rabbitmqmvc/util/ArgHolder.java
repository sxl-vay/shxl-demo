package top.boking.rabbitmqmvc.util;

public class ArgHolder {
    private static double multiple = 1;

    public static void setMultiple(double multiple) {
        ArgHolder.multiple = multiple;
    }
    public static double getMultiple() {
        if (multiple <= 0) {
            return 0;
        }
        return multiple;
    }
}
