package com.example.demo.trace.hellotrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloTraceV1Test {

    @Test
    public void begin_end() throws Exception {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("TEst");
        trace.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("helllo");
        trace.exception(status, new IllegalStateException());

    }
}