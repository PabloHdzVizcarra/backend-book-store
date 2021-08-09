package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtImpl implements JwtService {
    byte[] secretKey =
            Base64.getDecoder().decode("Nw9ZoPEo6tKSoeJEt96OwBp2SjphHTRQhdxeOaxluNc=");
    SecretKey key = Keys.hmacShaKeyFor(secretKey);

    public JwtImpl() {
    }

    @Override
    public String createCookie(String email, String password) {
        Map<String, Object> passAndEmail = new HashMap<>();
        passAndEmail.put("password", password);
        passAndEmail.put("email", email);

        Instant now = Instant.now();

        return Jwts.builder()
                .setIssuer("pablo")
                .setSubject("user")
                .setAudience("all")
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .setHeader(passAndEmail)
                .signWith(key)
                .compact();
    }

    @Override
    public LoginData validateCookie(String cookie) throws IllegalArgumentException {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(cookie);
        } catch (JwtException error) {
            error.printStackTrace();
            throw new IllegalArgumentException(error.getMessage());
        }

        String email = (String) claimsJws.getHeader().get("email");
        String password = (String) claimsJws.getHeader().get("password");

        return new LoginData(email, password);
    }
}
