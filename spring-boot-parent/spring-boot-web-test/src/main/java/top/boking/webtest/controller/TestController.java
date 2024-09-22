package top.boking.webtest.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.boking.bootstartertest.TestStartConfigFromJar;
import top.boking.webtest.TestStartConfig;

/**
 * @Author shxl
 * @Date 2024/9/19 20:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/t")
public class TestController {

    @Autowired
    private TestStartConfig testStartConfig;

    @Autowired
    private TestStartConfigFromJar ttt;

    @GetMapping("/a")
    public String a() {
        String test = testStartConfig.getTest();
        return "test";
    }
}
