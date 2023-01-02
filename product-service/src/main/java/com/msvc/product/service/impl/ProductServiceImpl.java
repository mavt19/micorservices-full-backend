package com.msvc.product.service.impl;

import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import com.msvc.product.model.Product;
import com.msvc.product.repository.ProductRepository;
import com.msvc.product.service.ProductService;
import com.msvc.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Mono<ProductResponse> create(ProductRequest productRequest) {
        Product product = ProductMapper.dtoToEntity(productRequest);
        log.info("ProductService -- saving product {}", productRequest);
        return productRepository.save(product)
                .map(ProductMapper::entityToDto);
    }

    @Override
    public Flux<ProductResponse> findAll() {
        log.info("ProductService -- finding products ");
        return productRepository.findAll().map(ProductMapper::entityToDto);
    }
}
