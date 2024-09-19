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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleItem that)) return false;
        return Objects.equals(getValue(), that.getValue());
    }*/



    @Override
    public int hashCode() {
        return 1;
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {




        ConcurrentHashMap<SingleItem,Integer> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i1 = 0; i1 < 3; i1++) {
            concurrentHashMap.putAll(/*Map.of(new SingleItem(i1),null)*/null);
        }
        Integer i1 = concurrentHashMap.get(new SingleItem(89));

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
