package com.example.demo.app.v2;

import com.example.demo.app.v1.OrderRepositoryV1;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
