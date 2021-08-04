package com.pablojvm.infrastructure;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class LoginHandler implements HttpHandler
{
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);
        InputStream requestBody = exchange.getRequestBody();
        int i;
        while ((i = requestBody.read()) != -1)
        {
            stringBuilder.append((char) i);
        }

        System.out.println(stringBuilder);
    }
}
