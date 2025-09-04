package com.hamza.springstore.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemUpdateDto {
    @NotNull(message = "Quantity can not be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @Max(value = 100, message = "Quantity must be less than or equal to 100 ")
    private Integer quantity;
}
