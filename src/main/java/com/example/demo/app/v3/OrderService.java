package com.example.demo.app.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public void orderItem(String itemId) {
        log.info("OrderService.orderItem()");
        orderRepository.save(itemId);
    }
}
