package com.hamza.springstore.filters;

import com.hamza.springstore.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

//      Extract Jwt token
        if (authHeader != null && !authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response); // Pass control to the next filter in the filter chain
          return;
        }
        assert authHeader != null;
        var token = authHeader.replace("Bearer ", "");
        if(!jwtService.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
//      Create an Authentication object
        var authentication = new UsernamePasswordAuthenticationToken(
                jwtService.getIdFromToken(token),
                null,
                null
        );
//      Attach request details for logging/security
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

//      Stores it
        SecurityContextHolder.getContext().setAuthentication(authentication);

//     Continue processing the request
        filterChain.doFilter(request, response);

    }
}
