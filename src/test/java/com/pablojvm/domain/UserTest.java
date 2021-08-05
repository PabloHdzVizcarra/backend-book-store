package com.pablojvm.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
    @Test
    void createUserAndPasswordIsHashed()
    {
        String name = "john";
        String lastname = "Connor";
        String email = "test@test.com";
        String password = "admin123";

        User user = new User(name, lastname, email, password);

        System.out.println(user);
        assertNotEquals(password, user.getPassword());
    }
}