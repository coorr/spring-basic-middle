package com.example.demo.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {


    public void external() {
        log.info("call external");
        internal(); //외부 메서드 호출
    }
    public void internal() {
        log.info("call internal");
    }
}
