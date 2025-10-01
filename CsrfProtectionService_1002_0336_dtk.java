// 代码生成时间: 2025-10-02 03:36:20
package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CsrfProtectionService {

    // ConcurrentHashMap to store CSRF tokens
    private static final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    @GetMapping("/token")
    public String generateToken(HttpServletRequest request) {
        // Generate a new CSRF token and store it in the map
        String token = UUID.randomUUID().toString();
        tokenMap.put(request.getRemoteAddr(), token);
        return token;
    }

    @GetMapping("/validate")
    public String validateToken(HttpServletRequest request, HttpServletResponse response) {
        String tokenFromMap = tokenMap.getOrDefault(request.getRemoteAddr(), "");
        String tokenFromRequest = request.getParameter("csrfToken");

        // Check if tokens match
        if (tokenFromMap.equals(tokenFromRequest)) {
            // Tokens match, remove token from map
            tokenMap.remove(request.getRemoteAddr());
            return "Token validated successfully.";
        } else {
            // Tokens do not match, send error response
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "CSRF validation failed.";
        }
    }

    // Additional methods for CSRF protection can be added here
}
