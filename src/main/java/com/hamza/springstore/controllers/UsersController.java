package com.hamza.springstore.controllers;

import com.hamza.springstore.dtos.RegisterUserRequest;
import com.hamza.springstore.dtos.UserDto;
import com.hamza.springstore.mappers.UserMapper;
import com.hamza.springstore.repositories.UserRepository;
import com.hamza.springstore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public Iterable<UserDto> getUsers( @RequestParam( name = "sort", required = false, defaultValue = "") String sort ) {
        if(!Set.of("name","email").contains(sort))
            sort = "name";
        return userRepository.findAll(Sort.by(sort).descending()).stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

}
