package com.example.demo.trace.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    public void basicConfg() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicPostConfigProcessor.class);

//        A a = context.getBean("beanA", A.class);
//        a.helloA();
        B b = context.getBean(B.class);
        b.helloB();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(A.class));



    }

    @Slf4j
    @Configuration
    static class BasicPostConfigProcessor {
        @Bean
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor hello() {
            return new AToBPostProcessor();
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

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", bean, beanName);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }

}
