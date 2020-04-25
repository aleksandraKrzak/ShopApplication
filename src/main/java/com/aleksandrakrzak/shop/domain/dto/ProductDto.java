package com.aleksandrakrzak.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // nie zwraca nullowym wartości
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;

}
