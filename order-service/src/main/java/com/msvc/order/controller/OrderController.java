package com.msvc.order.controller;

import com.msvc.order.dto.OrderRequest;
import com.msvc.order.dto.OrderResponse;
import com.msvc.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<OrderResponse>> create(@Valid @RequestBody OrderRequest orderRequest){

        return orderService.placeOrder(orderRequest)
                .map(ResponseEntity::ok).log();

    }
}
