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

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public Mono<Boolean> isInStock(String skuCode) {

        return Mono.just(inventoryRepository.findBySkuCode(skuCode).isPresent());
    }

    @Override
    public Mono<InventoryResponse> create(InventoryRequest inventoryRequest) {
        return Mono.just(inventoryRequest)
                .map(inventoryMapper::dtoToEntity)
                .map(inventoryRepository::save)
                .map(inventoryMapper::entityToDto)
                .log();
    }

    @Override
    public Flux<InventoryResponse> findAll() {
        return Flux.fromIterable(inventoryRepository.findAll()).map(inventoryMapper::entityToDto);
    }
}
