package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationServiceTest
{

    @Nested
    class validateFieldsWithError
    {
        private final String name = "john";
        private final String errorName = "asd";
        private final String lastname = "connor";
        private final String errorLastname = "ab";
        private final String email = "test@test.com";
        private final String errorEmail = "invalidEmail.com";
        private final String password = "admin123";
        private final String errorPassword = "123";
        ValidationService service;

        @BeforeEach
        void init()
        {
            service = new ValidationService();
        }

        @Test
        void validateNameWithError()
        {
            DataPostUser data = new DataPostUser(errorName, lastname, email, password);


            List<String> listErrors = service.validateDataCreateUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateLastnameWithError()
        {
            DataPostUser data = new DataPostUser(name, errorLastname, email, password);


            List<String> listErrors = service.validateDataCreateUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateEmailWithError()
        {
            DataPostUser data = new DataPostUser(name, lastname, errorEmail, password);
            List<String> list = service.validateDataCreateUser(data);

            assertEquals(list.size(), 1);
        }
    }
}