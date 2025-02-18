package top.boking.springbootjetcachemvc.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.springbootjetcachemvc.service.JetCacheAService;

/**
 * @Author shxl
 * @Date 2024/9/26 21:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/jetcache")
public class JetcacheController {

    @Resource
    private JetCacheAService jetCacheAService;

    @GetMapping("/key")
    public String consumer1(@RequestParam String key) {
        return jetCacheAService.getCacheKey(key);
    }

    @GetMapping("/put")
    public String update(@RequestParam String key,@RequestParam String value) {
        jetCacheAService.updateUser(key,value);
        return "success";
    }
}
