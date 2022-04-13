package com.example.demo.config.v2_dynamicproxy;

import com.example.demo.app.v1.*;
import com.example.demo.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import com.example.demo.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {
    private static final String[] PATTERN = {"request*","order*","save*"};

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderService(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderControllerV1,trace,PATTERN));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepository(trace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceV1,trace,PATTERN));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepositoryV1,trace,PATTERN));
        return proxy;
    }
}
