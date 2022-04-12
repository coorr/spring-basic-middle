package com.example.demo.trace.decorator;

import com.example.demo.trace.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    public void no() throws Exception {
        RealComponent realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
        client.execute();
        client.execute();

    }

    @Test
    public void yes() throws Exception {
        RealComponent realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();

    }

    @Test
    public void yes3() throws Exception {
        Component realComponent = new RealComponent();
        Component timeDecorator = new TimeDecorator(realComponent);
        Component messageDecorator = new MessageDecorator(timeDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();

    }
}
