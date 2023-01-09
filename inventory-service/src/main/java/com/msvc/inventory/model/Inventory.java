package com.msvc.inventory.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;


@Table(schema = "public", name = "inventories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Inventory {
    @Id
    private Long id;

    @Column(value = "sku_code")
    private String skuCode;

    private Integer quantity;
}