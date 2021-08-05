package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.infrastructure.MysqlDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPersistenceServiceTest
{
    @Test
    void saveUserCorrectly()
    {
        MysqlDB mysqlDB = new MysqlDB();

        UserPersistenceService service =
                new UserPersistenceService(mysqlDB);

        DataPostUser data = new DataPostUser(
                "john", "connor", "test@example.com", "admin123");

        service.saveUser(data);
    }
}