package com.example.demo.config.v1_proxy.concrete_proxy;

import com.example.demo.app.v2.OrderRepositoryV2;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 orderRepository;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
