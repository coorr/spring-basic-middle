package com.example.demo.trace.proxy;

import com.example.demo.trace.proxy.code.CacheProxy;
import com.example.demo.trace.proxy.code.ProxyPatternClient;
import com.example.demo.trace.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    public void noProxyTest() throws Exception {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient proxy = new ProxyPatternClient(realSubject);
        proxy.execute();
        proxy.execute();
        proxy.execute();

    }

    @Test
    public void yesProxyTest() throws Exception {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient proxy = new ProxyPatternClient(cacheProxy);
        proxy.execute();
        proxy.execute();
        proxy.execute();

    }
}
