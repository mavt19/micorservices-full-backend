package com.msvc.order.util;

import com.msvc.order.dto.OrderLineItemsDto;
import com.msvc.order.dto.OrderRequest;
import com.msvc.order.model.Order;
import com.msvc.order.model.OrderLineItems;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {

    public Order dtoToModel(OrderRequest orderRequest){

        Order order =  Order.builder()
                        .orderNumber(UUID.randomUUID().toString())
                        .build();
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsListDtos()
                .stream().map(x->  OrderMapper.orderLineItemsDtoToModel(x, order))
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        return order;
    }

    private static OrderLineItems orderLineItemsDtoToModel(OrderLineItemsDto orderLineItemsDto, Order order) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .order(order)
                .build();
    }
}
