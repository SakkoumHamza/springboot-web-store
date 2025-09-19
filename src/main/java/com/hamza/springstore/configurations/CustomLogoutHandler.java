package com.hamza.springstore.configurations;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        var authHeader = request.getHeaders("Authorization");


        // Clear the refreshToken cookie
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // keep if using HTTPS
        cookie.setPath("/auth/refresh");    // same as when it was set
        cookie.setMaxAge(0);    // expire immediately
        response.addCookie(cookie);
    }
}
