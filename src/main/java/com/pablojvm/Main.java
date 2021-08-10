package com.pablojvm;

import com.pablojvm.infrastructure.JacksonImpl;
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

        AppControllers appController = new AppControllers(
                new UserService(new UserPersistenceService(new MysqlDB())),
                new ValidationService(),
                new JacksonImpl()
        );

        Javalin app = Javalin.create().start(8082);
        app.post("/api/v1/user/", appController::createUser);
        app.post("/api/v1/user/login", appController::loginUser);
        app.get("/api/v1/user/auth", appController::autoLogin);
        // TODO: 8/10/21 create new endpoint delete a user
        app.delete("/api/v1/user/auth", appController::deleteUser);
    }
}
