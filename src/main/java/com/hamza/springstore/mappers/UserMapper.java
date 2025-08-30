package com.hamza.springstore.mappers;


import com.hamza.springstore.dtos.RegisterUserRequest;
import com.hamza.springstore.dtos.UserDto;
import com.hamza.springstore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
