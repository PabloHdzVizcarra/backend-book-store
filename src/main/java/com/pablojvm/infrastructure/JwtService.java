package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

import org.jetbrains.annotations.Nullable;

public interface JwtService {
    String createCookie(String email, String password);

    /**
     * Validates whether the cookie being passed is valid or invalid returning the
     * stored data.
     *
     * @param cookie A cookie in string format
     * @return A {@link LoginData} object or null
     */
    @Nullable LoginData validateCookie(String cookie);
}
