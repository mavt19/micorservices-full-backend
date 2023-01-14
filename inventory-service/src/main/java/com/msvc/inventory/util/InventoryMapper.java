package com.msvc.inventory.util;

import com.msvc.inventory.dto.InventoryRequest;
import com.msvc.inventory.dto.InventoryResponse;
import com.msvc.inventory.model.Inventory;
import com.msvc.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InventoryMapper {
    private final InventoryRepository inventoryRepository;

    public InventoryMapper(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse entityToDto(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .isInStock(inventory.getQuantity()>0)
                .build();
    }

    public Inventory dtoToEntity(InventoryRequest inventoryRequest){
        return Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }
}
