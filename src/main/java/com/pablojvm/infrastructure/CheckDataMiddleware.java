package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationData;
import com.pablojvm.domain.DataUser;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.javalin.http.Context;

public class CheckDataMiddleware extends Middleware {
    @Override
    public boolean check(RequestData requestData) {
        Context httpContext = requestData.getHttpContext();
        Logger loggerService = requestData.getLoggerService();
        ValidationData validationService = requestData.getValidationService();
        DataUser data = requestData.getDataUser();
        List<String> errorsList = validationService.createUser(data);

        if (errorsList.size() != 0) {
            loggerService.log(Level.INFO
                    , "the following errors due to incorrect data occurred when trying " +
                            "to create a user: " + errorsList);

            httpContext.status(400);
            httpContext.json(errorsList);
            return false;
        }

        return checkNext(requestData);
    }
}
