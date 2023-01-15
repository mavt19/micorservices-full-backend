package com.msvc.inventory.controller;

import com.msvc.inventory.dto.InventoryRequest;
import com.msvc.inventory.dto.InventoryResponse;
import com.msvc.inventory.service.InventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public Flux<InventoryResponse> findAll(){

        return inventoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InventoryResponse> create(@Valid @RequestBody InventoryRequest inventoryRequest){

        return inventoryService.create(inventoryRequest).log();
    }

    @GetMapping("/in-stock")
    public Flux<InventoryResponse> isInStock(@NotBlank @RequestParam("sku-codes") List<String> skuCodes){

        return inventoryService.isInStock(skuCodes);
    }
}
