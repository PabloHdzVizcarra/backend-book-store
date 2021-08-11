package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationData;
import com.pablojvm.user.UserService;

import java.util.logging.Logger;

import io.javalin.http.Context;

public class RequestData {
    private Context httpContext;
    private Mapper mapper;
    private ValidationData validationService;
    private Logger logger;
    private UserService userService;
    private JwtService jwtService;

    public RequestData(
            Context httpContext,
            Mapper mapper,
            ValidationData validationService,
            Logger logger,
            UserService userService,
            JwtService jwtService
    ) {
        this.httpContext = httpContext;
        this.mapper = mapper;
        this.validationService = validationService;
        this.logger = logger;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public Context getHttpContext() {
        return httpContext;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public ValidationData getValidationService() {
        return validationService;
    }

    public Logger getLogger() {
        return logger;
    }

    public UserService getUserService() {
        return userService;
    }

    public JwtService getJwtService() {
        return jwtService;
    }
}
