package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest
{
    @Test
    void validateNameWithError()
    {
        ValidationService service = new ValidationService();
        String errorName = "do";
        String lastname = "connor";
        String email = "test@tes.com";
        String password = "admin123";
        DataPostUser data = new DataPostUser(errorName, lastname, email, password);


        List<String> listErrors = service.validateDataCreateUser(data);

        assertEquals(listErrors.size(), 1);
    }

    @Test
    void validateLastnameWithError()
    {
        ValidationService service = new ValidationService();
        String name = "john";
        String lastname = "co";
        String email = "test@tes.com";
        String password = "admin123";
        DataPostUser data = new DataPostUser(name, lastname, email, password);


        List<String> listErrors = service.validateDataCreateUser(data);

        assertEquals(listErrors.size(), 1);
    }
}