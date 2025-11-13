package com.santa.api_gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    public static String SECRET;

    public JwtUtil() {
        SECRET = "6f8ee1551b04bcb74ad883736ecf7322993c09392ee9652013dd56859dab1a63";
    }

    public void validateToken(final String token) {
        Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
