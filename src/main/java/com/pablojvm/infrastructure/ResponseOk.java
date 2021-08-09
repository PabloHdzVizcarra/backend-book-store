package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

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
}
