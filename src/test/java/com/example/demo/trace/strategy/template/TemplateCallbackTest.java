package com.example.demo.trace.strategy.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
    public void callbackV1() throws Exception {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직1 실행"));




    }
}
