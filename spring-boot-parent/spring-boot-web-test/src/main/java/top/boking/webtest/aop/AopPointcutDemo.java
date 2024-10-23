package top.boking.webtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 只定义切入点类，这里证明了切入点可以在任意位置上
 * @Author shxl
 * @Date 2024/10/22 20:32
 * @Version 1.0
 */
public class AopPointcutDemo {
    /**
     * 指定注解切入点
     */
    @Pointcut("@annotation(top.boking.webtest.aop.AopT1Annotation)")
    public void pointcutT1Annotation() {
    }


}
