package com.msvc.order.client.serviceInventory.service;

import com.msvc.order.client.serviceInventory.dto.InventoryResponse;
import com.msvc.order.util.exceptions.ServerRestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceClient {
    private final WebClient.Builder webClientBuilder;

    public Flux<InventoryResponse> isInStock(List<String> skuCodes){
        log.info("calling {} whit sku codes = {}", InventoryServiceClient.class , skuCodes);
        return webClientBuilder.build().get()
                .uri("http://inventory-service-app/api/v1/inventory/in-stock", uriBuilder -> uriBuilder.queryParam("sku-codes", skuCodes).build())
                .retrieve()
                .bodyToFlux(InventoryResponse.class)
                .onErrorMap(ServerRestException::new)
                .log();
    }

}
