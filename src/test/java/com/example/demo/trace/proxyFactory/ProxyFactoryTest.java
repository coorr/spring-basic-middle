package com.example.demo.trace.proxyFactory;

import com.example.demo.trace.common.advice.TimeAdvice;
import com.example.demo.trace.common.service.ConcreteService;
import com.example.demo.trace.common.service.ServiceImpl;
import com.example.demo.trace.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyFactoryTest {

    @Test
    public void proxy() throws Exception {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.find();
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        System.out.println("AopUtils.isJdkDynamicProxy() = " + AopUtils.isJdkDynamicProxy(proxy));
        System.out.println("AopUtils.isJdkDynamicProxy() = " + AopUtils.isCglibProxy(proxy));

    }
}
