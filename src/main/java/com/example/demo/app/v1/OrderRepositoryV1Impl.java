package com.example.demo.app.v1;

import com.example.demo.util.Util;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    @Override
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        Util.sleep(1000);

    }
}
