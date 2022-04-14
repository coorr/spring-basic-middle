package com.example.demo.internalcall;

import com.example.demo.internalcall.aop.CallLogAcpect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(CallLogAcpect.class)
@Slf4j
class CallServiceV0Test {

    @Autowired
    CallServiceV1 callServiceV1;
    @Test
    void external() {
        callServiceV1.external();
    }


}