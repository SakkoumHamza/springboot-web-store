package com.hamza.springstore.controllers;

import com.hamza.springstore.dtos.RegisterUserRequest;
import com.hamza.springstore.dtos.UpdatePasswordRequest;
import com.hamza.springstore.dtos.UpdateUserRequest;
import com.hamza.springstore.dtos.UserDto;
import com.hamza.springstore.entities.Role;
import com.hamza.springstore.mappers.UserMapper;
import com.hamza.springstore.repositories.UserRepository;
import com.hamza.springstore.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UsersController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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

    @PostMapping("/create")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        if(userRepository.existsByEmail(request.getEmail()))
            return
                ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri  ).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ){
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        userMapper.updateUser(request,user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(
            @PathVariable(name = "id") Long id
    ){
        var user = userRepository.findById(id).orElse(null);

        if(user == null)
            return ResponseEntity.notFound().build();

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/update-password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdatePasswordRequest request
    ){
        var user = userRepository.findById(id).orElse(null);

        if(user == null)
            return ResponseEntity.notFound().build();

        if(!user.getPassword().equals(request.getOldPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
