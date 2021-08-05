package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.application.ValidationService;
import com.pablojvm.domain.DataPostUser;

import java.util.List;

import io.javalin.http.Context;

public class UsersController
{
    private final ValidationService validationService;

    public UsersController(ValidationService service)
    {
        this.validationService = service;
    }

    public void createUser(Context context) throws JsonProcessingException
    {

        String body = context.body();
        ObjectMapper objectMapper = new ObjectMapper();
        DataPostUser data =
                objectMapper.readValue(body, new TypeReference<>()
                {
                });

        List<String> errorsList =
                this.validationService.validateDataCreateUser(data);

        System.out.println(errorsList);


        context.json(data);
    }
}
