package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

public interface Mapper {
    LoginData createLoginData(String body) throws IllegalArgumentException;
}
