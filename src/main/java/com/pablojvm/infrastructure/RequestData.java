package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationData;
import com.pablojvm.domain.DataUser;
import com.pablojvm.user.UserService;

import java.util.logging.Logger;

import io.javalin.http.Context;

public class RequestData {
    private final Context httpContext;
    private final Mapper mapper;
    private final ValidationData validationService;
    private final Logger logger;
    private final UserService userService;
    private final JwtService jwtService;
    private DataUser dataUser;

    public RequestData(
            Context httpContext,
            Mapper mapper,
            ValidationData validationService,
            Logger logger,
            UserService userService,
            JwtService jwtService,
            DataUser dataUser
    ) {
        this.httpContext = httpContext;
        this.mapper = mapper;
        this.validationService = validationService;
        this.logger = logger;
        this.userService = userService;
        this.jwtService = jwtService;
        this.dataUser = dataUser;
    }

    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
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
