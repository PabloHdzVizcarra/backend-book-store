package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class ResponseOk {
    public void withCookie(Context context, LoginData dataRequest) {
        Map<String, Object> passAndEmail = new HashMap<>();
        passAndEmail.put("password", dataRequest.getPassword());
        passAndEmail.put("email", dataRequest.getEmail());
        byte[] secret = Base64.getDecoder().decode("tOX" +
                "+BgAa4v1jS0Kjvs9gRtpdiNtWHwvekd7VgNUUJwo=");
        Instant now = Instant.now();

        String jwt = Jwts.builder()
                .setSubject("admin")
                .setHeader(passAndEmail)
                .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        System.out.println(jwt);

    }
}
