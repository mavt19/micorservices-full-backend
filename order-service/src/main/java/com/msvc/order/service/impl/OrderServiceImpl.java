package com.msvc.order.service.impl;

import com.msvc.order.client.serviceInventory.dto.InventoryResponse;
import com.msvc.order.client.serviceInventory.service.InventoryServiceClient;
import com.msvc.order.dto.OrderLineItemsDto;
import com.msvc.order.dto.OrderRequest;
import com.msvc.order.dto.OrderResponse;
import com.msvc.order.repository.OrderRepository;
import com.msvc.order.service.OrderService;
import com.msvc.order.util.OrderMapper;
import com.msvc.order.util.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final InventoryServiceClient inventoryServiceClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Override
    public Mono<OrderResponse> placeOrder(OrderRequest orderRequest) {
        List<String> skuCodes = orderRequest.getOrderLineItemsListDtos().stream().map(OrderLineItemsDto::getSkuCode).toList();

        return inventoryServiceClient.isInStock(skuCodes)
                .switchIfEmpty(Mono.error(new NotFoundException("skuCodes " + skuCodes+ " not found")))
                .doOnNext(x-> filterHasStock(x, skuCodes))
                .count()
                .map(x-> orderRequest)
                    .map(orderMapper::dtoToModel)
                    .map(orderRepository::save)
                    .map(response -> OrderResponse.builder()
                            .id(response.getId())
                            .orderNumber(response.getOrderNumber())
                            .build()
                    ).log();
    }

    private void filterHasStock(InventoryResponse inventoryList, List<String> skuCodes) {
        System.out.println("entro"+ inventoryList);
            if(Boolean.FALSE.equals(inventoryList.getIsInStock())){
                throw new NotFoundException("skuCode " + inventoryList.getSkuCode()+ " doesn't have stock");
            };

        skuCodes.forEach(x-> {
            if(!inventoryList.getSkuCode().contains(x)){
                throw new NotFoundException("skuCode " + x + " not found");
            }
        });
    }
}
