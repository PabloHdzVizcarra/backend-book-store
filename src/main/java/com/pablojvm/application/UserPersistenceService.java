package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public class UserPersistenceService
{
    private final CrudOperationsDB mysqlDB;

    public UserPersistenceService(CrudOperationsDB mysqlDB)
    {
        this.mysqlDB = mysqlDB;
    }

    public void saveUser(DataPostUser data)
    {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );

        this.mysqlDB.saveUser(userToSave);
    }
}
