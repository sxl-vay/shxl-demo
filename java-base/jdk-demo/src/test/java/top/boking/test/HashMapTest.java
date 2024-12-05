package top.boking.test;

import tok.boking.POJO.SingleItem;
import tok.boking.POJO.SingleTreeMapItem;

import java.util.*;

/**
 * @Author shxl
 * @Date 2024/9/4 20:13
 * @Version 1.0
 */
public class HashMapTest {
    public static void main(String[] args) {
       // singlePut();
        stringPut();

    }

    private static void stringPut() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("5", "1");
        map.put("6", "1");
        map.put("7", "1");
        map.put("8", "1");
        map.put("9", "1");
        map.put("10", "1");
        map.put("11", "1");
        map.put("12", "1");
        map.put("13", "1");
        map.put("14", "1");
    }

    private static void singlePut() {
        Map<SingleTreeMapItem,String> map = new HashMap<>();
        map.put(new SingleTreeMapItem(1),"1");
        map.put(new SingleTreeMapItem(2),"2");
        map.put(new SingleTreeMapItem(3),"3");
        map.put(new SingleTreeMapItem(4),"4");
        Collection<String> values = map.values();
        map.put(new SingleTreeMapItem(5),"5");
        map.put(new SingleTreeMapItem(6),"6");
        Iterator<String> iterator = values.iterator();
        String next = iterator.next();
        System.out.println("next = " + next);
        System.out.println(values);
        values.forEach(s-> System.out.println("s = " + s));
    }
}
