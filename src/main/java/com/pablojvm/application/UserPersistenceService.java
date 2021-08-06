package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public class UserPersistenceService
{
    private final operationsDatabase mysqlDB;

    public UserPersistenceService(operationsDatabase mysqlDB)
    {
        this.mysqlDB = mysqlDB;
    }

    // TODO: 8/5/21 get the primary key generate method saveUser
    // TODO: 8/5/21 set the primary key in User object
    // TODO: 8/5/21 return User object with all data
    public User saveUser(DataPostUser data)
    {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );

        int idUser = this.mysqlDB.saveUser(userToSave);
        if (idUser == 0)
        {
            throw new IllegalArgumentException("The email: " + userToSave.getEmail() +
                    " already use");
        }

        userToSave.setId(idUser);
        return userToSave;
    }
}
