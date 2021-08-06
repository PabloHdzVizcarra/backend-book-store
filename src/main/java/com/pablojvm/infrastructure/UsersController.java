package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.application.ActionsPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

import java.util.Collections;
import java.util.List;

import io.javalin.http.Context;

public class UsersController {
    private final ValidationService validationService;
    private final ActionsPersistenceService userPersistenceService;

    public UsersController(
            ValidationService service,
            ActionsPersistenceService userPersistenceService
    ) {
        this.validationService = service;
        this.userPersistenceService = userPersistenceService;
    }

    public void createUser(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper objectMapper = new ObjectMapper();
        DataPostUser data =
                objectMapper.readValue(body, new TypeReference<>() {
                });

        List<String> errorsList =
                this.validationService.validateDataCreateUser(data);
        User saveUser = this.userPersistenceService.saveUser(data);

        if (errorsList.size() != 0) {
            this.responseCreateUserWithError(context, errorsList);
        } else if (saveUser == null) {
            this.responseWithInvalidEmail(context);
        } else {
            context.status(201);
            context.json(saveUser);
        }
    }

    private void responseWithInvalidEmail(Context context) {
        context.status(400);
        context.result("The email is duplicated, please add another email.");
    }

    private void responseCreateUserWithError(
            Context context,
            List<String> errorsList
    ) {
        errorsList.add("You have some values with invalidad data, please check this " +
                "values");
        Collections.reverse(errorsList);
        context.status(400);
        context.json(errorsList);
    }
}
