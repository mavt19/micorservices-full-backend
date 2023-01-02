package com.msvc.order.service.impl;

import com.msvc.order.dto.OrderRequest;
import com.msvc.order.dto.OrderResponse;
import com.msvc.order.repository.OrderRepository;
import com.msvc.order.service.OrderService;
import com.msvc.order.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public Mono<OrderResponse> placeOrder(OrderRequest orderRequest) {

        return Mono.just(orderRequest)
                .map(orderMapper::dtoToModel)
                .map(orderRepository::save)
                .map(response -> OrderResponse.builder()
                        .id(response.getId())
                        .orderNumber(response.getOrderNumber())
                        .build()
                ).log();
    }
}
