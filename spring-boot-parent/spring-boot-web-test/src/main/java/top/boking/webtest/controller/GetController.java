package top.boking.webtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class GetController {
    @GetMapping(params = "a=a")
    public String a() {
        return "a";
    }

    @GetMapping(params = "b=b")
    public String b() {
        return "b";
    }
}
