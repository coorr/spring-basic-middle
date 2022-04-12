package com.example.demo.trace.strategy.strategy1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {


    @Test
    public void strategyV0() throws Exception {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

    }

    @Test
    public void strategyV2() throws Exception {
        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        contextV2.execute();

    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime= endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime= endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

}
