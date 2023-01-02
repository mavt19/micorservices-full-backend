package com.msvc.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {
    private String idResponse;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
