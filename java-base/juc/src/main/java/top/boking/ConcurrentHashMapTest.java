package top.boking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> ccMap = new ConcurrentHashMap<>();
        List<CompletableFuture<Void>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AtomicInteger ai = new AtomicInteger(i);
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                for (int j = 0; j < 10000; j++) {
                    ccMap.put(ai + "_" + j, ai + "_" + j);
                }
            });
            list.add(voidCompletableFuture);
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        ccMap.put("1", "1");
        ccMap.put("2", "2");
        ccMap.put("3", "3");
        ccMap.put("4", "4");
        ccMap.put("5", "5");
        ccMap.put("6", "6");
        ccMap.put("7", "7");
        ccMap.put("8", "8");
        ccMap.put("9", "9");
        ccMap.put("10", "10");
        ccMap.put("11", "11");
        ccMap.put("12", "12");
        ccMap.put("13", "13");
        ccMap.put("14", "14");
        ccMap.put("15", "15");
        ccMap.put("16", "16");

    }
}
