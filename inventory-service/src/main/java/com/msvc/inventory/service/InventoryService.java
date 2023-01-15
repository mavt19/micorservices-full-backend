package com.msvc.inventory.service;

import com.msvc.inventory.dto.InventoryRequest;
import com.msvc.inventory.dto.InventoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface InventoryService {

    Mono<Boolean> isInStock(String skuCode);

    Mono<InventoryResponse> create(InventoryRequest inventoryRequest);

    Flux<InventoryResponse> findAll();

    Flux<InventoryResponse> isInStock(List<String> skuCodes);
}
