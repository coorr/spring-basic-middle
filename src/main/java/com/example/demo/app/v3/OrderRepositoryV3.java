package com.example.demo.app.v3;

import com.example.demo.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        Util.sleep(1000);

    }
}
