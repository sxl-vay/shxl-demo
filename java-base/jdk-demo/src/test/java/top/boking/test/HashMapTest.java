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
    public synchronized static void main(String[] args) {
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
