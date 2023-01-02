package com.msvc.product.util;

import com.msvc.product.dto.ProductRequest;
import com.msvc.product.dto.ProductResponse;
import com.msvc.product.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public static ProductResponse entityToDto(Product product){
        return ProductResponse.builder()
                .idResponse(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .build();
    }

    public static Product dtoToEntity(ProductRequest product){
        return Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
