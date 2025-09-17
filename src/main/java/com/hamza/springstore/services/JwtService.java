package com.hamza.springstore.services;

import com.hamza.springstore.configurations.JwtConfig;
import com.hamza.springstore.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    public Jwt generateAccessToken(User user) {
        return generateToken(user,jwtConfig.getAccessTokenExpiration());
    }

    public Jwt parse(String token) {
        try {
        var claims = getClaims(token);
        return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (Exception e) {
            return null;
        }
    }

    private Jwt generateToken(User user,long tokenExpiration) {
        var claims =Jwts.claims()
                .add(Map.of("name", user.getName(), "email", user.getEmail(),"role", user.getRole()))
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        return new Jwt(claims, jwtConfig.getSecretKey());
    }

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
