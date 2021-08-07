package com.pablojvm.application;

import com.pablojvm.domain.DataUser;
import com.pablojvm.user.LoginData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testable
class ValidationServiceTest {

    @Nested
    class validateFieldsWithErrors {
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
        void init() {
            service = new ValidationService();
        }

        @Test
        void validateNameWithError() {
            DataUser data = new DataUser(errorName, lastname, email, password);
            List<String> listErrors = service.createUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateLastnameWithError() {
            DataUser data = new DataUser(name, errorLastname, email, password);


            List<String> listErrors = service.createUser(data);

            assertEquals(listErrors.size(), 1);
        }

        @Test
        void validateEmailWithError() {
            DataUser data = new DataUser(name, lastname, errorEmail, password);
            List<String> list = service.createUser(data);

            assertEquals(list.size(), 1);
        }

        @Test
        void validatePasswordWithError() {
            DataUser data = new DataUser(name, lastname, email, errorPassword);
            List<String> list = service.createUser(data);

            assertEquals(list.size(), 1);
        }

        @Test
        void allFieldsNotValid() {
            DataUser data =
                    new DataUser(errorName, errorLastname, errorEmail, errorPassword);
            List<String> list = service.createUser(data);

            assertEquals(list.size(), 4);
        }


    }

    @Nested
    class validateLoginData {
        private final String email = "test@test.com";
        private final String errorEmail = "invalidEmail.com";
        private final String password = "admin123";
        private final String errorPassword = "123";
        ValidationService service;

        @BeforeEach
        void init() {
            service = new ValidationService();
        }

        @Test
        void validateErrorEmail() {
            LoginData data = new LoginData(errorEmail, password);
            List<String> errors = service.loginData(data);

            assertEquals(1, errors.size());
        }

        @Test
        void validateNullEmail() {
            LoginData data = new LoginData(errorEmail, password);
            data.setEmail(null);
            List<String> errors = service.loginData(data);

            assertEquals(1, errors.size());
        }

        @Test
        void validateCorrectEmail() {
            LoginData data = new LoginData(email, password);
            List<String> errors = service.loginData(data);

            assertEquals(0, errors.size());
        }

        @Test
        void validateNullPassword() {
            LoginData data = new LoginData(email, password);
            data.setPassword(null);
            List<String> errors = service.loginData(data);

            assertEquals(1, errors.size());
        }

        @Test
        void validInvalidPassword() {
            LoginData data = new LoginData(email, errorPassword);
            List<String> errors = service.loginData(data);

            assertEquals(1, errors.size());
        }

        @Test
        void withPasswordAndEmailInvalid() {
            LoginData data = new LoginData(errorEmail, errorPassword);
            List<String> errors = service.loginData(data);

            assertEquals(2, errors.size());
        }
    }
}