package com.pablojvm.infrastructure;

import java.util.List;

import io.javalin.http.Context;

public class ResponseErrorController {
    public void withInvalidEmail(Context context) {}
    public void createUser(Context context, List<String> errorList) {}
}
