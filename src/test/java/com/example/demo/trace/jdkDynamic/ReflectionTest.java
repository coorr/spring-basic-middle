package com.example.demo.trace.jdkDynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    public void reflection0() throws Exception {
        Hello hello = new Hello();
        log.info("start");
        String result1 = hello.callA();
        log.info("result={}", result1);

        log.info("start");
        String result2 = hello.callB();
        log.info("result={}", result2);

    }

    @Test
    public void reflection1() throws Exception {
        Class classHello = Class.forName("com.example.demo.trace.jdkDynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);

    }

    @Test
    public void reflection2() throws Exception {
        Class classHello = Class.forName("com.example.demo.trace.jdkDynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodCallA = classHello.getMethod("callA");
        Method methodCallB = classHello.getMethod("callB");

        dynamicCall(methodCallA, target);
        dynamicCall(methodCallB, target);

    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}

