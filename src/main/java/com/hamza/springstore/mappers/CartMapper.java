package com.hamza.springstore.mappers;

import com.hamza.springstore.dtos.CartDto;
import com.hamza.springstore.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CartMapper {
    Cart toEntity(CartDto cartDto);
    CartDto toDto(Cart cart);
}
