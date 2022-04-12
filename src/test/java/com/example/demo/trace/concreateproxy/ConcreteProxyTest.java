package com.example.demo.trace.concreateproxy;

import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    public void yesProxy() throws Exception {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy proxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();

    }
}
