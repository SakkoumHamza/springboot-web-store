package com.hamza.springstore.services;


import com.hamza.springstore.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
@ToString
@Data
public class Jwt {
    private final Claims claims ;
    private SecretKey secretKey;

    public boolean isExpired(String token){
        try{
            return claims.getExpiration().before(new Date());
        } catch ( JwtException e) {
            return false;
        }
    }
    public Long getUserId(){
        return Long.valueOf(claims.getSubject());
    }

    public Role getRole(){
        return Role.valueOf(claims.get("role").toString());
    }
    public String toString(){
        return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
