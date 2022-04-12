package com.example.demo.config.v1_proxy.interface_proxy;

import com.example.demo.app.v1.OrderRepositoryV1;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceV1 implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            target.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
