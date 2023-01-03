package com.msvc.inventory.util;

import com.msvc.inventory.dto.InventoryRequest;
import com.msvc.inventory.dto.InventoryResponse;
import com.msvc.inventory.model.Inventory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InventoryMapper {

    public InventoryResponse entityToDto(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }

    public Inventory dtoToEntity(InventoryRequest inventoryRequest){
        return Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }
}
