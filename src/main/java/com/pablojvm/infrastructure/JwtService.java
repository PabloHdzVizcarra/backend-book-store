package com.pablojvm.infrastructure;

public interface JwtService {
    String createCookie(String email, String password);
}
