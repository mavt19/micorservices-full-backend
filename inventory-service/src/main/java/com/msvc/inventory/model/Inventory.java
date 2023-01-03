package com.msvc.inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;

    private Integer quantity;
}
