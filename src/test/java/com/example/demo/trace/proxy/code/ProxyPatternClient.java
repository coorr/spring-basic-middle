package com.example.demo.trace.proxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProxyPatternClient {
    private final Subject subject;

    public void execute() {
        subject.operation();
    }
}
