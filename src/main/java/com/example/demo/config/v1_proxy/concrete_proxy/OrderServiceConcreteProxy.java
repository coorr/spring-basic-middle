package com.example.demo.config.v1_proxy.concrete_proxy;

import com.example.demo.app.v2.OrderServiceV2;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;



public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 orderService;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderServiceV2 orderService, LogTrace trace) {
        super(null);
        this.orderService = orderService;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderService.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
