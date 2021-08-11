package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;
import com.pablojvm.user.User;
import com.pablojvm.user.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.javalin.http.Context;

public class SaveUserMiddleware extends Middleware {
    @Override
    public boolean check(RequestData requestData) {
        Context httpContext = requestData.getHttpContext();
        UserService userService = requestData.getUserService();
        DataUser data = requestData.getDataUser();
        Logger loggerService = requestData.getLoggerService();
        User userFromDatabase = userService.saveUser(data);

        if (userFromDatabase == null) {
            loggerService.log(
                    Level.INFO,
                    "failed to save the user in the database"
            );
            httpContext.status(400);
            httpContext.result("The email is duplicated, please add another email");
            return false;
        }
        loggerService.log(
                Level.INFO,
                "the next user is created in the database: " + userFromDatabase
        );
        httpContext.status(201);
        httpContext.json(userFromDatabase);
        return checkNext(requestData);
    }
}
