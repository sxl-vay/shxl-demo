package top.boking.redismvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;

@SpringBootTest
class SpringBootRedisMvcApplicationTests {

    @Test
    void contextLoads() {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("111","12");
        objectObjectHashMap.compute("111", (k, v) -> {
            return null;
        });


    }

}
