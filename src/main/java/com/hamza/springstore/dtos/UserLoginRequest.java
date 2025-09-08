package com.hamza.springstore.dtos;


import com.hamza.springstore.validation.Lowercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank
    @Lowercase
    private String email;

    @NotBlank
    @Size(min = 8, max = 20,message = "Password must be greater than 8 and lower to 20 characters")
    private String password;

}
