package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;


public interface JwtService {
    String createCookie(String email, String password);

    /**
     * Validates whether the cookie being passed is valid or invalid returning the
     * stored data.
     *
     * @param cookie A cookie in string format
     * @return A {@link LoginData} object or null
     * @throws IllegalArgumentException if there is an error validating the cookie
     */
    LoginData validateCookie(String cookie) throws IllegalArgumentException;
}
