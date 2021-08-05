package com.pablojvm;

import com.pablojvm.application.ValidationService;
import com.pablojvm.infrastructure.UsersController;

import io.javalin.Javalin;

// TODO: 8/4/21 create endpoint post user

public class Main
{
    public static void main(String[] args)
    {
        UsersController usersController = new UsersController(new ValidationService());

        Javalin app = Javalin.create().start(8081);
        app.post("/login", usersController::createUser);
    }
}
