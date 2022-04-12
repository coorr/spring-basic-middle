package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloTraceV2Test {

    @Test
    public void begin_end() throws Exception {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status1);
        trace.end(status2);
    }

    @Test
    public void begin_exception() throws Exception {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("helllo");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());

    }
}