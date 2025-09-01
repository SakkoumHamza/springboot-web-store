package com.hamza.springstore.dtos;


import com.hamza.springstore.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "Name is required")
    @NotBlank
    private String name;

    @Lowercase
    @Email(message = "This not a correct email form")
    @NotBlank
    private String email;

    @Size(min = 6, max = 20,message = "Password must be between 6 and 20 characters")
    @NotBlank
    private String password;
}
