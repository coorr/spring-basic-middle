package com.example.demo.config.v1_proxy.interface_proxy;

import com.example.demo.app.v1.OrderServiceV1;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {
    private final OrderServiceV1 target;
    private final LogTrace trace;


    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
