package com.pablojvm;

import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.MysqlDB;
import com.pablojvm.user.UserService;
import com.pablojvm.user.UserController;
import com.pablojvm.util.LoggingUtil;

import java.io.IOException;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) throws IOException {
        LoggingUtil.initLogManager();

        UserController userController = new UserController(
                new UserService(
                        new ValidationService(),
                        new UserPersistenceService(
                                new MysqlDB())
                ));

        Javalin app = Javalin.create().start(8082);
        app.post("/login", userController::createUser);
    }
}
