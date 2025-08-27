package top.boking.webtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import top.boking.webtest.controller.TestController;
import top.boking.webtest.service.AopService;
import top.boking.webtest.transation.service.ShxlClassService;

@SpringBootTest
class SpringBootMvcApplicationTests {
    @Autowired
    private ShxlClassService shxlClassService;


    @Test
    void contextLoads() {

        shxlClassService.removeByClassNo("SE001");


    }

}
