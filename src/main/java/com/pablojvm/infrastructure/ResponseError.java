package com.pablojvm.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.javalin.http.Context;

public class ResponseError {
    public void withInvalidEmail(Context context) {
        context.status(400);
        context.result("The email is duplicated, please add another email.");

    }

    public void createUser(Context context, List<String> errorList) {
        Collections.reverse(errorList);
        context.status(400);
        context.json(errorList);

    }

    /**
     * Create a response when the user sends invalid data when tying to log in.
     *
     * @param context a {@link Context} object
     * @param errors A list containing error messages
     */
    public void withInvalidLoginData(Context context, List<String> errors) {
        List<String> list = new ArrayList<>(errors);
        list.add("Invalidad data was sent when logging a user in.");
        Collections.reverse(list);
        context.status(400);
        context.json(list);
    }

    public void withMessage(Context context, String message) {
        context.status(404);
        context.result(message);
    }
}
