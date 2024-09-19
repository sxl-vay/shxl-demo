package top.boking.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author shxl
 * @Date 2024/9/5 22:26
 * @Version 1.0
 */
public class QueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 11; i++) {
            strings.offer("i"+i);
        }
        System.out.println(strings.peek());
        System.out.println("strings = " + strings);

    }
}
