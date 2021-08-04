package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.domain.DataPostUser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LoginHandler implements HttpHandler
{

    private final UtilsHttpHandlers utilsHttpHandlers;

    public LoginHandler(UtilsHttpHandlers utilsHttpHandlers)
    {
        this.utilsHttpHandlers = utilsHttpHandlers;
    }

    // TODO: 8/4/21 validate request body
    // TODO: 8/4/21 -- return message error when body is not valid
    @Override
    public void handle(HttpExchange exchange)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try
        {
            requestBody = utilsHttpHandlers.getRequestBody(exchange);
            DataPostUser dataPostUser = objectMapper.readValue(
                    requestBody,
                    new TypeReference<>()
                    {
                    }
            );
            System.out.println(dataPostUser);


        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
