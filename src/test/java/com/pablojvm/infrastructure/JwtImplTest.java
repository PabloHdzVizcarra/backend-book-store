package com.pablojvm.infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtImplTest {
    @Test
    void validateCorrectCookie() {
        JwtImpl jwt = new JwtImpl();
        String cookie =
                jwt.createCookie("example@example.com", "admin123");

        jwt.validateCookie(cookie);
    }
}