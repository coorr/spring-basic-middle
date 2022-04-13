package com.example.demo.config.v3_proxyfactory;

import com.example.demo.app.v1.*;
import com.example.demo.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfig1 {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderService(trace));
        ProxyFactory factory = new ProxyFactory(orderControllerV1);
        factory.addAdvisor(getAdvisor(trace));
        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), orderControllerV1.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1 OrderServiceV1 = new OrderServiceV1Impl(orderRepository(trace));
        ProxyFactory factory = new ProxyFactory(OrderServiceV1);
        factory.addAdvisor(getAdvisor(trace));
        com.example.demo.app.v1.OrderServiceV1 proxy = (com.example.demo.app.v1.OrderServiceV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), OrderServiceV1.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(orderRepositoryV1);
        factory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), orderRepositoryV1.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
