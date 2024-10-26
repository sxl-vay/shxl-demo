package top.boking.springbootseatatesttm.controller;

import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.springbootseatatesttm.service.SeataService;
import top.boking.springbootseatatesttm.tcc.TccDemoService;

import java.util.HashMap;

/**
 * @Author shxl
 * @Date 2024/9/19 20:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/seata/tm")
public class TestController {

    @Resource
    private TccDemoService tccDemoService;

    @Resource
    private SeataService service;

    @GetMapping("/dubboTest")
    public Object dubboTest(String name) {
        return service.rpcGet(name);
    }


    @GetMapping(value = "/testCommit")
    @GlobalTransactional
    public Object testCommit(@RequestParam(name = "id", defaultValue = "1") Integer id,
                             @RequestParam(name = "sum", defaultValue = "1") Integer sum) {
        tccDemoService.insert(new HashMap<>(){{put("id",id);}});
        /*Boolean ok = productService.reduceStock(id, sum);
        if (ok) {
            LocalDateTime now = LocalDateTime.now();
            Orders orders = new Orders();
            orders.setCreateTime(now);
            orders.setProductId(id);
            orders.setReplaceTime(now);
            orders.setSum(sum);
            orderService.save(orders);
            return "ok";
        } else {
            return "fail";
        }*/
        return "ok";
    }
}
