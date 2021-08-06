package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.application.UserPersistenceService;
import com.pablojvm.application.ValidationService;
import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

import java.util.Collections;
import java.util.List;

import io.javalin.http.Context;

public class UsersController
{
    private final ValidationService validationService;
    private final UserPersistenceService userPersistenceService;

    public UsersController(
            ValidationService service,
            UserPersistenceService userPersistenceService
    )
    {
        this.validationService = service;
        this.userPersistenceService = userPersistenceService;
    }

    public void createUser(Context context) throws JsonProcessingException
    {

        // TODO: 8/5/21 add service logging messages
        String body = context.body();
        ObjectMapper objectMapper = new ObjectMapper();
        DataPostUser data =
                objectMapper.readValue(body, new TypeReference<>()
                {
                });

        List<String> errorsList =
                this.validationService.validateDataCreateUser(data);

        if (errorsList.size() != 0)
        {
            this.responseCreateUserWithError(context, errorsList);

        } else
        {
            User saveUser = this.userPersistenceService.saveUser(data);

            context.status(201);
            context.json(saveUser);
        }
    }

    private void responseCreateUserWithError(
            Context context,
            List<String> errorsList
    )
    {
        errorsList.add("You have some values with invalidad data, please check this " +
                "values");
        Collections.reverse(errorsList);
        context.status(400);
        context.json(errorsList);
    }
}
