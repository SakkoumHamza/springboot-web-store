package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.JwtResponse;
import com.hamza.springstore.dtos.UserDto;
import com.hamza.springstore.dtos.UserLoginRequest;
import com.hamza.springstore.mappers.UserMapper;
import com.hamza.springstore.repositories.UserRepository;
import com.hamza.springstore.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody UserLoginRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        //   Return a json token
        var token  = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("validate")
    public boolean validateToken(
            @RequestHeader("Authorization") String authHeader
    ){
            String token = authHeader.replace("Bearer ", "");
            return jwtService.validateToken(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) auth.getPrincipal();

        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok().body(userDto);
    }


//    Exceptions handlers

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
