package com.msvc.order.service;

import com.msvc.order.dto.OrderRequest;
import com.msvc.order.dto.OrderResponse;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<OrderResponse> placeOrder(OrderRequest orderRequest);
}
