package com.example.demo.trace.proxy.code;

import com.example.demo.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements  Subject{
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        Util.sleep(1000);
        return "data";
    }
}
