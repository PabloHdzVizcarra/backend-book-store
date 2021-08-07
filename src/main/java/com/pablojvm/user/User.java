package com.pablojvm.user;


import com.pablojvm.domain.Password;

public class User
{
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
    private Integer id;


    public User(String name, String lastname, String email, String password)
    {
        this.name = name.toLowerCase();
        this.lastname = lastname.toLowerCase();
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

    public Integer getId()
    {
        return id;
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
