package com.hamza.springstore.mappers;


import com.hamza.springstore.dtos.ProductDto;
import com.hamza.springstore.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    void update(ProductDto productDto, @MappingTarget Product product);
}
