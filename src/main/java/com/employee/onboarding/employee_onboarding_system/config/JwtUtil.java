package com.employee.onboarding.employee_onboarding_system.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "my-super-secure-and-strong-jwt-secret-key-256-bits";

    private Key getSigningKey() {
        byte[] decodedKey = Base64.getEncoder().encode("my-super-secure-and-strong-jwt-secret-key-256-bits".getBytes());
        return new SecretKeySpec(decodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    /** Validate signature, expiration */
    public Claims validateAndGetAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /** Extract username (sub) */
    public String extractUsername(String token) {
        return validateAndGetAllClaims(token).getSubject();
    }

    /** Throw error if invalid */
    public void validateToken(String token) {
        validateAndGetAllClaims(token); // If invalid → throws exception
    }

}
