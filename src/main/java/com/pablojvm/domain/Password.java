package com.pablojvm.domain;

import org.mindrot.jbcrypt.BCrypt;


public class Password {

    public static String create(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean compare(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
}
