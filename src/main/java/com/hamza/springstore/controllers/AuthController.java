package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.JwtResponse;
import com.hamza.springstore.dtos.UserLoginRequest;
import com.hamza.springstore.services.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
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
        //   Return a json token
        var token  = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("validate")
    public boolean validateToken(
            @RequestHeader("Authorization") String authHeader
    ){
            String token = authHeader.replace("Bearer ", "");
            return jwtService.validateToken(token);
    }



//    Exceptions handlers

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
