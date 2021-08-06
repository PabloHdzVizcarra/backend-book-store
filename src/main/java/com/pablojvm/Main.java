package com.pablojvm;

import com.pablojvm.application.UserPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.MysqlDB;
import com.pablojvm.infrastructure.UsersController;
import com.pablojvm.util.LoggingUtil;

import java.io.IOException;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) throws IOException {
        LoggingUtil.initLogManager();

        UsersController usersController = new UsersController(
                new ValidationService(),
                new UserPersistenceService(new MysqlDB())
        );

        Javalin app = Javalin.create().start(8082);
        app.post("/login", usersController::createUser);
    }
}
