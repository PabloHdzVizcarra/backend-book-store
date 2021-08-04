package com.pablojvm.domain;

public class DataPostUser
{
    private String name;
    private String lastname;
    private String email;
    private String password;

    public DataPostUser()
    {
    }

    @Override
    public String toString()
    {
        return "DataPostUser{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
