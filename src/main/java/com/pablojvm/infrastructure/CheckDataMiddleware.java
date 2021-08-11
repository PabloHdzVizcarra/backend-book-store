package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationData;
import com.pablojvm.domain.DataUser;

import java.util.List;

public class CheckDataMiddleware extends Middleware {
    @Override
    public boolean check(RequestData requestData) {
        ValidationData validationService = requestData.getValidationService();
        DataUser data = requestData.getDataUser();
        List<String> errorsList = validationService.createUser(data);

        if (errorsList.size() != 0) {
            System.out.println("you have errors: " + errorsList);
            return false;
        }

        return true;
    }
}
