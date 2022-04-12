package com.example.demo.config.v1_proxy.interface_proxy;

import com.example.demo.app.v1.OrderControllerV1;
import com.example.demo.trace.TraceStatus;
import com.example.demo.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {
    private final OrderControllerV1 target;
    private final LogTrace trace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
