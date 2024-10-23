package top.boking.webtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author shxl
 * @Date 2024/10/22 20:32
 * @Version 1.0
 */
@Aspect
@Component
public class AopT1Aspect {




    @Around("top.boking.webtest.aop.AopPointcutDemo.pointcutT1Annotation()")
    public Object facade(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        System.out.println("args = " + args);
        Object response = pjp.proceed();
        System.out.println("response = " + response);
        return response+"shxl";
    }

    @After("@annotation(top.boking.webtest.aop.AopT1Annotation)")
    public void after() {
        System.out.println("top.boking.webtest.aop.AopT1Aspect.after done");
    }

    @AfterReturning("@annotation(top.boking.webtest.aop.AopT1Annotation)")
    public void afterReturning() {
        System.out.println("top.boking.webtest.aop.AopT1Aspect.afterReturning done");
    }

    @AfterThrowing("@annotation(top.boking.webtest.aop.AopT1Annotation)")
    public void afterThrowing() {
        System.out.println("top.boking.webtest.aop.AopT1Aspect.afterThrowing done");
    }

}
