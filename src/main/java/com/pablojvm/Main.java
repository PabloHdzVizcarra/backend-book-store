package com.pablojvm;

import com.pablojvm.infrastructure.JacksonImpl;
import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.MysqlDB;
import com.pablojvm.user.UserServiceImpl;
import com.pablojvm.infrastructure.AppControllers;
import com.pablojvm.util.LoggingUtil;

import java.io.IOException;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) throws IOException {
        LoggingUtil.initLogManager();

        AppControllers appController = new AppControllers(
                new UserServiceImpl(
                        new UserPersistenceService(new MysqlDB())),
                new ValidationService(),
                new JacksonImpl()
        );

        Javalin app = Javalin.create().start(8082);

        app.post("/api/v1/user", appController::createUser);
        app.post("/api/v1/user/login", appController::loginUser);
        app.get("/api/v1/user", appController::autoLogin);
        app.delete("/api/v1/user", appController::deleteUser);
    }
}
