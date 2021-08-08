package com.pablojvm.user;

import com.pablojvm.domain.Password;

public class User {
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;
    private Integer id;


    public User(String name, String lastname, String email, String password) {
        this.name = name.toLowerCase();
        this.lastname = lastname.toLowerCase();
        this.email = email;
        this.password = Password.create(password);
    }

    public User(
            Integer id,
            String name,
            String lastname,
            String email,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean comparePassword(String password) {
        return Password.compare(password, this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
