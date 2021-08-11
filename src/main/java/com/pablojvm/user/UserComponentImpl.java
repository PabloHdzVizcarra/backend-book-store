package com.pablojvm.user;

import com.pablojvm.application.ValidationData;
import com.pablojvm.infrastructure.JwtService;
import com.pablojvm.infrastructure.Mapper;
import com.pablojvm.infrastructure.RequestData;

import java.util.logging.Logger;

import io.javalin.http.Context;

public class UserComponentImpl implements UserComponent {
    private final Mapper mapper;
    private final ValidationData validationService;
    private final JwtService jwtService;
    private final Logger loggerService;

    public UserComponentImpl(
            Mapper mapper,
            ValidationData validationService,
            JwtService jwtService
    ) {
        this.mapper = mapper;
        this.validationService = validationService;
        this.jwtService = jwtService;
        this.loggerService = Logger.getLogger(UserComponentImpl.class.getName());
    }

    @Override
    public void loginUser(Context context) {
        new RequestData(
                context,
                mapper,
                validationService,
                loggerService,
                null,
                jwtService,
                null
        );
    }
}
