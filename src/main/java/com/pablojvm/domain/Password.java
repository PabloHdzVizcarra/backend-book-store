package com.pablojvm.domain;

import java.security.SecureRandom;

public class Password
{

    public static String create(String password)
    {
        SecureRandom random = new SecureRandom();
        return password;
    }
}
