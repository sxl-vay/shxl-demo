package top.boking.test;

import tok.boking.POJO.SingleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author shxl
 * @Date 2024/9/6 21:43
 * @Version 1.0
 */
public class ArrayListTest {

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 1_000_000_00L; i++) {
            strings.add(i+"");
        }
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }
}
