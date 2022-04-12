package com.example.demo.trace.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component{
    private final Component component;

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        String result = component.operation();
        String decoResult = "****" + result + "***";
        log.info("MEssage 꾸미기 전 = {}, 후는 = {}", result, decoResult);
        return decoResult;
    }
}
