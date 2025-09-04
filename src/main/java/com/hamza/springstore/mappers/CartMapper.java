package com.hamza.springstore.mappers;

import com.hamza.springstore.dtos.CartDto;
import com.hamza.springstore.dtos.CartItemDto;
import com.hamza.springstore.entities.Cart;
import com.hamza.springstore.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CartMapper {
    Cart toEntity(CartDto cartDto);

    @Mapping(target = "totalPrice",expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice",expression = "java(cartItem.getTotalPrice())")
    CartItemDto todto(CartItem cartItem);
}
