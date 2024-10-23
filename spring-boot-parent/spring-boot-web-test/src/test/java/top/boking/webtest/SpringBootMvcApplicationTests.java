package top.boking.webtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import top.boking.webtest.controller.TestController;
import top.boking.webtest.service.AopService;

@SpringBootTest
class SpringBootMvcApplicationTests {
    @Autowired
    private TestController testController;

    @Autowired
    private AopService aopService;

    @Test
    void contextLoads() {
        String s = aopService.t1("ttt");
        Assert.notNull(s,"s null");
    }

}
