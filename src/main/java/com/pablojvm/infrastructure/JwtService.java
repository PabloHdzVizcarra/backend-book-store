package com.pablojvm.infrastructure;

public interface JwtService {
    String createCookie(String email, String password);

    /**
     * Validates whether the cookie being passed is valid or invalid returning the
     * stored data.
     *
     * @param cookie A cookie in string format
     */
    void validateCookie(String cookie);
}
