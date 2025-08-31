package com.hamza.springstore.mappers;


import com.hamza.springstore.dtos.RegisterUserRequest;
import com.hamza.springstore.dtos.UpdateUserRequest;
import com.hamza.springstore.dtos.UserDto;
import com.hamza.springstore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
