package top.boking.redismvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@SpringBootTest
class SpringBootRedisMvcApplicationTests {

    @Test
    void contextLoads() {
        Jedis je = new Jedis("127.0.0.1",6379);
        String key = "a";
        je.watch(key);
        String value = je.get(key);
        Transaction multi = je.multi();
        multi.set(key,String.valueOf(Integer.parseInt(value)+1));
        if (multi.exec() == null) {
            System.out.println("fail");
        } else {
            System.out.println("ok");
        }

    }

}
