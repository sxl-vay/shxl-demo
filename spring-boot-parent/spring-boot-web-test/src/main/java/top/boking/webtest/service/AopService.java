package top.boking.webtest.service;

import org.springframework.stereotype.Service;
import top.boking.webtest.aop.AopT1Annotation;

/**
 * @Author shxl
 * @Date 2024/10/22 21:12
 * @Version 1.0
 */
@Service
public class AopService {
    @AopT1Annotation
    public String t1(String name) {
        return name;
    }

}
