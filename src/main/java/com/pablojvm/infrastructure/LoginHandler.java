package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.application.ValidationService;
import com.pablojvm.domain.DataPostUser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class LoginHandler implements HttpHandler
{

    private final UtilsHttpHandlers utilsHttpHandlers;
    private final ValidationService validationService;

    public LoginHandler(ValidationService validationService)
    {
        this.validationService = validationService;
        this.utilsHttpHandlers = new UtilsHttpHandlers();
    }

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
            List<String> errorsList =
                    validationService.validateDataCreateUser(dataPostUser);

            if (errorsList.size() != 0)
            {
                this.withErrorParamsResponse(exchange);
            }
            System.out.println(errorsList);

        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private void withErrorParamsResponse(
            HttpExchange exchange
    ) throws IOException
    {
        System.out.println("error data body");
        exchange.getResponseHeaders()
                .set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(201, 50);
        OutputStream outputStream = exchange.getResponseBody();

        outputStream.write(Byte.parseByte("some data"));
        outputStream.flush();
        outputStream.close();
    }
}
