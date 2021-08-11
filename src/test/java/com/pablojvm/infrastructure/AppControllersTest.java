package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationService;
import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.user.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.javalin.http.Context;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppControllersTest {

    private Context ctx;

    @BeforeEach
    void init() {
        ctx = mock(Context.class);
    }

    @Test
    void createUser() {
        when(ctx.body()).thenReturn("{\n" +
                "    \"name\": \"Pablo\",\n" +
                "    \"lastname\": \"Hernandez\",\n" +
                "    \"email\": \"javabest@jvm.com\",\n" +
                "    \"password\": \"admin123\"\n" +
                "}");

        AppControllers appControllers = new AppControllers(
                new UserServiceImpl(new UserPersistenceService(new MysqlDB())),
                new ValidationService(),
                new JacksonImpl()
        );

        appControllers.createUser(ctx);
    }
}