package com.example.demo.config.v3_proxyfactory;

import com.example.demo.app.v1.*;
import com.example.demo.app.v2.OrderControllerV2;
import com.example.demo.app.v2.OrderRepositoryV2;
import com.example.demo.app.v2.OrderServiceV2;
import com.example.demo.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProxyFactoryConfig2 {
    @Bean
    public OrderControllerV2 orderController(LogTrace trace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderService(trace));
        ProxyFactory factory = new ProxyFactory(orderControllerV2);
        factory.addAdvisor(getAdvisor(trace));
        OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), orderControllerV2.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace trace) {
        OrderServiceV2 OrderServiceV2 = new OrderServiceV2(orderRepository(trace));
        ProxyFactory factory = new ProxyFactory(OrderServiceV2);
        factory.addAdvisor(getAdvisor(trace));
        OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), OrderServiceV2.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace trace) {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
        ProxyFactory factory = new ProxyFactory(orderRepositoryV2);
        factory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, tartget={}", proxy.getClass(), orderRepositoryV2.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
