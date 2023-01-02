package com.msvc.product.service;

import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductResponse> create(ProductRequest productRequest);
    Flux<ProductResponse> findAll();
}
