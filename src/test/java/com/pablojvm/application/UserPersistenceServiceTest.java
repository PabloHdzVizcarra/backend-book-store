package com.pablojvm.application;

import com.pablojvm.domain.DataUser;
import com.pablojvm.infrastructure.MysqlDB;
import com.pablojvm.user.UserPersistenceService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceServiceTest
{
    @Test
    void saveUserWithDuplicatedEmail()
    {
        MysqlDB mysqlDB = new MysqlDB();

        UserPersistenceService service =
                new UserPersistenceService(mysqlDB);

        DataUser data = new DataUser(
                "john",
                "connor",
                "data@example.com",
                "admin123");

        assertThrows(IllegalArgumentException.class, () ->
                service.saveUser(data));
    }
}