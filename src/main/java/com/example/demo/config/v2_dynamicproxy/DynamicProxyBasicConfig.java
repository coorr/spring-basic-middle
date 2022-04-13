package com.example.demo.config.v2_dynamicproxy;

import com.example.demo.app.v1.*;
import com.example.demo.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.example.demo.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderService(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderControllerV1,trace));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepository(trace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderServiceV1,trace));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceBasicHandler(orderRepositoryV1,trace));
        return proxy;
    }
}
