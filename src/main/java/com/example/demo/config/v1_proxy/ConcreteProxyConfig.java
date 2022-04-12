package com.example.demo.config.v1_proxy;

import com.example.demo.app.v2.OrderControllerV2;
import com.example.demo.app.v2.OrderRepositoryV2;
import com.example.demo.app.v2.OrderServiceV2;
import com.example.demo.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.example.demo.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.example.demo.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.example.demo.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace trace) {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepositoryV2,trace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace trace) {
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepository(trace));
        return new OrderServiceConcreteProxy(orderServiceV2, trace);
    }

    @Bean
    public OrderControllerV2 orderController(LogTrace trace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderService(trace));
        return new OrderControllerConcreteProxy(orderControllerV2,trace);
    }
}
