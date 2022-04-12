package com.example.demo.config.v1_proxy.concrete_proxy;

import com.example.demo.app.v2.OrderControllerV2;
import com.example.demo.app.v2.OrderServiceV2;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 orderController;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 orderController, LogTrace trace) {
        super(null);
        this.orderController = orderController;
        this.trace = trace;
    }



    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = orderController.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return orderController.noLog();
    }
}
