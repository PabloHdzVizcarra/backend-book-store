package com.pablojvm.application;

import com.pablojvm.domain.LoginData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationServiceTest
{

    @Nested
    class validateFieldsWithErrors
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
            LoginData data = new LoginData(errorName, lastname, email, password);


            List<String> listErrors = service.validateDataCreateUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateLastnameWithError()
        {
            LoginData data = new LoginData(name, errorLastname, email, password);


            List<String> listErrors = service.validateDataCreateUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateEmailWithError()
        {
            LoginData data = new LoginData(name, lastname, errorEmail, password);
            List<String> list = service.validateDataCreateUser(data);

            assertEquals(list.size(), 1);
        }

        @Test
        void validatePasswordWithError()
        {
            LoginData data = new LoginData(name, lastname, email, errorPassword);
            List<String> list = service.validateDataCreateUser(data);

            assertEquals(list.size(), 1);
        }

        @Test
        void allFieldsNotValid()
        {
            LoginData data =
                    new LoginData(errorName, errorLastname, errorEmail, errorPassword);
            List<String> list = service.validateDataCreateUser(data);

            assertEquals(list.size(), 4);

        }
    }
}