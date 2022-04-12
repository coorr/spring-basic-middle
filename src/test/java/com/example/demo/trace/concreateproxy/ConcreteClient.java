package com.example.demo.trace.concreateproxy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteClient {
    private final ConcreteLogic concreteLogic;

    public void execute() {
        concreteLogic.operation();
    }
}
