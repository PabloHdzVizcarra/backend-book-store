package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pablojvm.user.UserService;

import io.javalin.http.Context;

public class AppControllers {
    private final UserService userService;
    private final ResponseErrorController responseErrorController;

    public AppControllers(UserService userService) {
        this.userService = userService;
        this.responseErrorController = new ResponseErrorController();
    }

    public void createUser(Context context) throws JsonProcessingException {
        userService.createUser(context);
    }
}
