package top.boking.springbootseatatesttm.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.boking.springbootseatatesttm.dao.domain.Post;
import top.boking.springbootseatatesttm.service.SeataService;

/**
 * @Author shxl
 * @Date 2024/9/19 20:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/seata/tm")
public class TestController {

    @Resource
    private SeataService service;

    @GetMapping("/dubboTest")
    public Object dubboTest(String name) {
        return service.rpcGet(name);
    }


    @PostMapping(value = "/testSeata")
    public Object testSeata(@RequestBody Post post) {
        String s = service.testSeata(post);
        return s;
    }

    @PostMapping(value = "/tcc")
    public Object tcc(@RequestBody Post post) {
        String s = service.tcc(post);
        return s;
    }
}
