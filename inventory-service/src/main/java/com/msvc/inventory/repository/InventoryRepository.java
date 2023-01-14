package com.msvc.inventory.repository;

import com.msvc.inventory.model.Inventory;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {
    @Query("SELECT * FROM inventories WHERE sku_code= $1 LIMIT 1")
    Mono<Inventory> findBySkuCode(String skuCode);

    @Query("SELECT * FROM inventories WHERE sku_code= $1")
    Flux<Inventory> findBySkuCodeFlux(String skuCode);
    Flux<Inventory> findBySkuCodeIn(List<String> skuCodes);
    @Query("SELECT * FROM inventories WHERE sku_code IN (:skuCodes)")
    Flux<Inventory> findBySkuCodes(List<String> skuCodes);
}
