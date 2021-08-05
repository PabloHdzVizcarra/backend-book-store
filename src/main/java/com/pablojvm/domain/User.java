package com.pablojvm.domain;

import java.util.UUID;

// TODO: 8/5/21 hash password correctly
public class User
{
    private final UUID uuid;
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;


    public User(String name, String lastname, String email, String password)
    {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = Password.create(password);
        this.uuid = UUID.randomUUID();
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
