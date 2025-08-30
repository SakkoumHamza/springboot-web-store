package com.hamza.springstore.mappers;


import com.hamza.springstore.dtos.ProductDto;
import com.hamza.springstore.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}
