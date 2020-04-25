package com.aleksandrakrzak.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientOrderDto {

    private String id;
    private Long productId;
    private String orderNumber;
    private String productName;
    private String productDescription;
    private Double price;
    private Double quantity;

}
