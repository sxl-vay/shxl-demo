package top.boking.webtest.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @GetMapping
    public String log(Integer count) {
        for (Integer i = 0; i < count; i++) {
            log.info("log{}",i);
        }
        return "log";
    }
}
