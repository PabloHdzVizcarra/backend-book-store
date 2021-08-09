package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtImplTest {
    @Test
    void validateCorrectCookie() {
        String email = "example@example.com";
        String password = "admin123";
        JwtImpl jwt = new JwtImpl();
        String cookie =
                jwt.createCookie(email, password);

        LoginData data = jwt.validateCookie(cookie);
        assertEquals(data.getEmail(), email);
    }
}