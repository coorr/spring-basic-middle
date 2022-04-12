package com.example.demo.trace.strategy.strategy1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ContextV1 {
    private final Strategy strategy;

    public void execute() {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime= endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
