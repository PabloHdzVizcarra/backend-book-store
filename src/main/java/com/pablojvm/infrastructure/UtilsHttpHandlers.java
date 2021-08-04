package com.pablojvm.infrastructure;

import com.sun.net.httpserver.HttpExchange;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class UtilsHttpHandlers
{

    public UtilsHttpHandlers()
    {
    }

    /**
     * Convert the body request from JSON format to string format.
     *
     * @param exchange A {@link HttpExchange} with the HTTP method data.
     * @return The body of the request in string format.
     * @throws IOException if there is an error reading the body
     */
    public String getRequestBody(@NotNull HttpExchange exchange) throws IOException
    {
        InputStream requestBody = exchange.getRequestBody();
        StringBuilder stringBuilder = new StringBuilder();

        int i;
        while ((i = requestBody.read()) != -1)
        {
            stringBuilder.append((char) i);
        }

        return stringBuilder.toString();
    }
}
