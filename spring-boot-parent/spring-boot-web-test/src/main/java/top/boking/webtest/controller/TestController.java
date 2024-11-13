package top.boking.webtest.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.boking.webtest.config.TestStartConfig;
import top.boking.webtest.service.AopService;

/**
 * @Author shxl
 * @Date 2024/9/19 20:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/t")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestStartConfig testStartConfig;



    @Resource
    private AopService aopService;

    @Value("${spring.datasource.url}")
    private String p;

    @GetMapping("/a")
    public String a(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        log.info(contextPath);
        return "shxl";
//        return aopService.t1(p);
    }
}
