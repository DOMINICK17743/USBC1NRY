// 代码生成时间: 2025-10-01 18:23:51
package com.example.security;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class CsrfProtectionService {

    // Generate a unique CSRF token
    public String generateCsrfToken() {
        return UUID.randomUUID().toString();
    }

    // Validate the CSRF token from the request
    public boolean validateCsrfToken(String token) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionToken = (String) request.getSession().getAttribute("CSRF_TOKEN");

        if (sessionToken == null || token == null || !sessionToken.equals(token)) {
            // Log the error and return false if the tokens don't match or if the session token is null
            // Error handling can be enhanced based on specific requirements
            return false;
        }
        return true;
    }

    // Add CSRF token to the session
    public void addCsrfTokenToSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String csrfToken = generateCsrfToken();
        request.getSession().setAttribute("CSRF_TOKEN", csrfToken);
    }
}
