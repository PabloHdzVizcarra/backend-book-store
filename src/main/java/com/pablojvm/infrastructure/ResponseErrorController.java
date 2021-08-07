package com.pablojvm.infrastructure;

import java.util.Collections;
import java.util.List;

import io.javalin.http.Context;

public class ResponseErrorController {
    public void withInvalidEmail(Context context) {
        context.status(400);
        context.result("The email is duplicated, please add another email.");

    }

    public void createUser(Context context, List<String> errorList) {
        Collections.reverse(errorList);
        context.status(400);
        context.json(errorList);

    }
}
