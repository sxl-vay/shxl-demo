package top.boking.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        InheritableThreadLocal<Map<String, Object>> mapInheritableThreadLocal = new InheritableThreadLocal<Map<String, Object>>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        mapInheritableThreadLocal.set(map);
        CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Map<String, Object> stringObjectMap = mapInheritableThreadLocal.get();
                System.out.println(stringObjectMap);
            }
        });
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mapInheritableThreadLocal.get().put("key1","value"+i);
        }
    }
}
