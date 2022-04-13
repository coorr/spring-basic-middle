package com.example.demo.trace.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    public void basicConfg() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);

        A a = context.getBean("beanA", A.class);
        a.helloA();

    }

    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("Hello A");
          }
        }
    @Slf4j
    static class B {
        public void helloB() {
            log.info("Hello B");
        }
    }

}
