package com.pablojvm.infrastructure;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtImpl implements JwtService {
    @Override
    public String createCookie(String email, String password) {
        Map<String, Object> passAndEmail = new HashMap<>();
        passAndEmail.put("password", password);
        passAndEmail.put("email", email);

        byte[] secret = Base64.getDecoder().decode("tOX" +
                "+BgAa4v1jS0Kjvs9gRtpdiNtWHwvekd7VgNUUJwo=");
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject("admin")
                .setHeader(passAndEmail)
                .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }
}
