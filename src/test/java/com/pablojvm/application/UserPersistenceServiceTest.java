package com.pablojvm.application;

import com.pablojvm.domain.LoginData;
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

        LoginData data = new LoginData(
                "john",
                "connor",
                "data@example.com",
                "admin123");

        assertThrows(IllegalArgumentException.class, () ->
                service.saveUser(data));
    }
}