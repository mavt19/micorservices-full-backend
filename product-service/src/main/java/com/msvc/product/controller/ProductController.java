package com.msvc.product.controller;

import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import com.msvc.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest){

//        URI uri = URI.create("/api/v1/product");
//        return productService.create(productRequest).log()
//                .map((res) -> ResponseEntity.created(uri).body(res));
                return productService.create(productRequest).log();
    }

    @GetMapping
    public Flux<ProductResponse> findAll(){

        return productService.findAll();
    }
}
