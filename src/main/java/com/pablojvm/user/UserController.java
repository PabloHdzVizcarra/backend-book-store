package com.pablojvm.user;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.javalin.http.Context;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Context context) throws JsonProcessingException {
        userService.createUser(context);
    }
}
