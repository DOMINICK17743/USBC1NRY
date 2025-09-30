// 代码生成时间: 2025-09-30 20:12:58
package com.example.security;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

@Service
public class CsrfProtectionService {
    
    // Generate a new CSRF token
    public String generateCsrfToken() {
        return UUID.randomUUID().toString();
    }
    
    // Retrieve CSRF token from request cookies
    public String getCsrfTokenFromCookies() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("CSRF-TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    // Set CSRF token as a cookie in the response
    public void setCsrfTokenInCookies(String csrfToken) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie cookie = new Cookie("CSRF-TOKEN", csrfToken);
        cookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
        cookie.setPath("/");
        request.getSession().setAttribute("CSRF-TOKEN", csrfToken);
        request.getResponse().addCookie(cookie);
    }
    
    // Check if the CSRF token in the request header matches the one in the session
    public boolean isValidCsrfToken(String tokenFromHeader, String tokenFromSession) {
        if (tokenFromHeader == null || tokenFromSession == null) {
            return false;
        }
        return tokenFromHeader.equals(tokenFromSession);
    }
    
    // Utility method to extract CSRF token from request header
    public String getCsrfTokenFromHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if ("X-CSRF-TOKEN".equalsIgnoreCase(headerName)) {
                return request.getHeader(headerName);
            }
        }
        return null;
    }
    
    // Method to verify CSRF token
    public boolean verifyCsrfToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tokenFromHeader = getCsrfTokenFromHeader(request);
        String tokenFromSession = (String) request.getSession().getAttribute("CSRF-TOKEN");
        return isValidCsrfToken(tokenFromHeader, tokenFromSession);
    }
    
    // Method to handle CSRF token mismatch
    public void handleCsrfTokenMismatch() {
        // Implement logic to handle CSRF token mismatch, e.g., throw an exception, log an error, etc.
        throw new RuntimeException("CSRF token mismatch detected.");
    }
}
