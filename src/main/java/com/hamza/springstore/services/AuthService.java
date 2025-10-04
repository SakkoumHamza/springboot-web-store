package com.hamza.springstore.services;

import com.hamza.springstore.entities.User;
import com.hamza.springstore.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public User getCurrentUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) auth.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }
}
