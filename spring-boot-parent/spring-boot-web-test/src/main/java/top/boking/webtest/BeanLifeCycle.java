package top.boking.webtest;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.boking.webtest.service.AopService;

@Component("beanLifeCycle")
public class BeanLifeCycle
        implements
        InitializingBean
        , BeanNameAware
        , BeanFactoryAware
    , BeanClassLoaderAware
    , BeanPostProcessor
{

    private AopService aopService;

    public void setAopService(AopService aopService) {
        System.out.println();
        this.aopService = aopService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader = " + classLoader);
    }

    public BeanLifeCycle() {
        System.out.println("BeanLifeCycle");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("postProcessBeforeInitialization--容器级别");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("postProcessAfterInitialization-- 容器级别");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
