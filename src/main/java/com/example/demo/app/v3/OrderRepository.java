package com.example.demo.app.v3;

import com.example.demo.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderRepository {

    public void save(String itemId) {
        log.info("OrderRepository.save()");

        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        Util.sleep(1000);

    }
}
