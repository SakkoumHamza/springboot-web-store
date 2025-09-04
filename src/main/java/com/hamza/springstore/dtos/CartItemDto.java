package com.hamza.springstore.dtos;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private CartProductDto product;
    private BigDecimal quantity;
    private BigDecimal TotalPrice;

}
