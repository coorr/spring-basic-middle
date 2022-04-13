package com.example.demo.trace.jdkDynamic;

import com.example.demo.trace.jdkDynamic.code.AImpl;
import com.example.demo.trace.jdkDynamic.code.AInterface;
import com.example.demo.trace.jdkDynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    public void dynamic() throws Exception {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());

    }
}
