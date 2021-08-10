package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;
import com.pablojvm.user.User;

import io.javalin.http.Context;

public class ResponseOk {
    private final JwtService jwtService;

    public ResponseOk() {
        this.jwtService = new JwtImpl();
    }

    public void withCookie(Context context, LoginData dataRequest) {
        String jwt = this.jwtService.createCookie(
                dataRequest.getEmail(),
                dataRequest.getPassword()
        );
        context.cookie("login", jwt);
        context.status(200);
    }

    public void withDataUser(Context context, User user) {
        context.status(200);
        context.json(user);
    }

    public void withMessage(Context context, String message) {
        context.status(201);
        context.result(message);
    }
}
