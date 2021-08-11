package com.pablojvm.user;

import io.javalin.http.Context;

public interface UserComponent {
    void loginUser(Context context);
}
