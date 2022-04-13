package com.example.demo.trace.aop;

import com.example.demo.app.v3.OrderRepository;
import com.example.demo.app.v3.OrderService;
import com.example.demo.app.v3.aop.AspectV1;
import com.example.demo.app.v3.aop.AspectV2;
import com.example.demo.app.v3.aop.AspectV3;
import com.example.demo.app.v3.aop.AspectV4Pointcut;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AspectV4Pointcut.class)
public class AopTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void appIncofo() throws Exception {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderRepository));

    }

    @Test
    public void success() throws Exception {
        orderService.orderItem("22");

    }

    @Test
    public void exception() throws Exception {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);

    }

}

