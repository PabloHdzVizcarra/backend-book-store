package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;
import com.pablojvm.infrastructure.MysqlDB;

public class UserPersistenceService
{
    private final MysqlDB mysqlDB;

    public UserPersistenceService(MysqlDB mysqlDB)
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
    }
}
