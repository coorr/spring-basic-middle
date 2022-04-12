package com.example.demo.trace.logtrace;

import com.example.demo.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FieldLogTraceTest {

    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    public void begin_end_level2() throws Exception {
        TraceStatus status1 = trace.begin("hlleo");
        TraceStatus status2 = trace.begin("hlleo2");

        trace.end(status2);
        trace.end(status1);


    }
    
    @Test
    public void begin_exception_lever2() throws Exception {
        TraceStatus status1 = trace.begin("hlleo");
        TraceStatus status2 = trace.begin("hlleo2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
            
    
    
    }
}