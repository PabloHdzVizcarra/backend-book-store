package com.pablojvm;

import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.MysqlDB;
import com.pablojvm.user.UserService;
import com.pablojvm.infrastructure.AppControllers;
import com.pablojvm.util.LoggingUtil;

import java.io.IOException;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) throws IOException {
        LoggingUtil.initLogManager();

        AppControllers userController = new AppControllers(
                new UserService(new UserPersistenceService(new MysqlDB())),
                new ValidationService()
        );

        Javalin app = Javalin.create().start(8082);
        app.post("/api/v1/user/", userController::createUser);
    }
}
