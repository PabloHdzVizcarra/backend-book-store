package com.pablojvm.domain;

import java.util.UUID;

// TODO: 8/5/21 hash password correctly
public class User
{
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
    private Integer id;


    public User(String name, String lastname, String email, String password)
    {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = Password.create(password);
    }

    public String getName()
    {
        return name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
