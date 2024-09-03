package tok.boking.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.ShxlThreadPool;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Author shxl
 * @Date 2024/9/1 19:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleItem implements Serializable {
    private Integer value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleItem that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }



    @Override
    public int hashCode() {
        return 1;
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, Integer> map = Map.of("aaaa", 1);
        Integer i1 = map.computeIfAbsent("b", k -> {
            System.out.println("shxlK," + k);
            return 121;
        });
        Integer i2 = map.computeIfPresent("aaaa", (k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
            return 998;
        });

        System.out.println(map);


        ConcurrentHashMap<SingleItem,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        ThreadPoolExecutor build = ShxlThreadPool.build();
        CompletableFuture<SingleItem> cf = new CompletableFuture<>();
        int x = 100000000;
        for (int i = 0; i < x; i++) {
            int finalI = i;
            CompletableFuture<Integer> sa1 = CompletableFuture.supplyAsync(() -> {concurrentHashMap.put(new SingleItem(finalI+x), finalI+x); return finalI+x;},build);
            System.out.println("sa1.join() = " + sa1.join());
            CompletableFuture<Integer> sa2 = CompletableFuture.supplyAsync(() -> {concurrentHashMap.put(new SingleItem(finalI), finalI); return finalI;},build);
            System.out.println("sa2.join() = " + sa2.join());
            CompletableFuture<Integer> integerCompletableFuture = sa1.thenCombine(sa2, Integer::sum);
            Integer join = integerCompletableFuture.join();
            System.out.println("join = " + join);
        }
    }
}
