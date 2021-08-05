package com.pablojvm.domain;

public class User
{
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
    }

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
