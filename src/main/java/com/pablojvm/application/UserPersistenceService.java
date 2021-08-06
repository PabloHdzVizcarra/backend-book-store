package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public class UserPersistenceService implements ActionsPersistenceService
{
    private final operationsDatabase mysql;

    public UserPersistenceService(operationsDatabase mysql)
    {
        this.mysql = mysql;
    }

    public User saveUser(DataPostUser data) throws IllegalArgumentException
    {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );

        int idUser = this.mysql.saveUser(userToSave);
        if (idUser == 0)
        {
            System.out.println("try save user with email duplicated");
            return null;
        }

        userToSave.setId(idUser);
        return userToSave;
    }
}
