package com.pablojvm.domain;

import org.mindrot.jbcrypt.BCrypt;


public class Password
{

    public static String create(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
