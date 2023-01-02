package com.msvc.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {
    @NotBlank(message = "El campo no puede ser nulo o blanco")
    @Size(min = 5, max = 200, message = "El tamaño debe estar emtre 5 y 200")
    private String name;
    private String description;
    @NotNull(message = "El campo no puede ser nulo o blanco")
    private BigDecimal price;
}
