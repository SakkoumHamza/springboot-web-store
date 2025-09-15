package com.hamza.springstore.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
@Tag(name = "Admin")
public class AdminController {
    @GetMapping("")
    public String getAdmin() {
        return "Hello Admin -_-!";
    }
}
