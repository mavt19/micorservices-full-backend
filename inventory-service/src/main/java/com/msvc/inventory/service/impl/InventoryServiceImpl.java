package com.msvc.inventory.service.impl;

import com.msvc.inventory.dto.InventoryRequest;
import com.msvc.inventory.dto.InventoryResponse;
import com.msvc.inventory.repository.InventoryRepository;
import com.msvc.inventory.service.InventoryService;
import com.msvc.inventory.util.InventoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public Mono<Boolean> isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode)
                .map(x-> true)
                .defaultIfEmpty(false);
    }

    @Override
    public Mono<InventoryResponse> create(InventoryRequest inventoryRequest) {
        return Mono.just(inventoryRequest)
                .map(inventoryMapper::dtoToEntity)
                .flatMap(inventoryRepository::save)
                .map(inventoryMapper::entityToDto)
                .log();
    }

    @Override
    public Flux<InventoryResponse> findAll() {
        return inventoryRepository.findAll().map(inventoryMapper::entityToDto);
    }

    @Override
    public Flux<InventoryResponse> isInStock(List<String> skuCodes) {
//        inventoryRepository.findBySkuCodes(skuCodes).log().subscribe();
        return inventoryRepository.findBySkuCodeIn(skuCodes).map(inventoryMapper::entityToDto);
    }
}