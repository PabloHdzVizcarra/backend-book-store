package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.application.ValidationData;
import com.pablojvm.domain.DataUser;
import com.pablojvm.user.LoginData;
import com.pablojvm.user.User;
import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.user.UserService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.javalin.http.Context;

public class AppControllers {
    private final UserService userService;
    private final ResponseErrorController errorController;
    private final ValidationData validationService;
    private final Mapper mapper;
    private static final Logger LOGGER =
            Logger.getLogger(UserPersistenceService.class.getName());

    public AppControllers(
            UserService userService,
            ValidationData validationService,
            Mapper mapper
    ) {
        this.userService = userService;
        this.validationService = validationService;
        this.mapper = mapper;
        this.errorController = new ResponseErrorController();
    }

    public void createUser(Context context) throws JsonProcessingException {
        String body = context.body();
        ObjectMapper objectMapper = new ObjectMapper();
        DataUser data =
                objectMapper.readValue(body, new TypeReference<>() {
                });

        List<String> errorsList =
                validationService.createUser(data);

        User saveUser = userService.saveUser(data);

        if (errorsList.size() != 0) {
            this.errorController.createUser(context, errorsList);
            LOGGER.log(
                    Level.INFO,
                    "an attempt was made to create a user with the following" +
                            "invalid data: " + errorsList
            );
        } else if (saveUser == null) {
            LOGGER.log(
                    Level.INFO,
                    "failed to save the user in the database"
            );
            this.errorController.withInvalidEmail(context);
        } else {
            LOGGER.log(
                    Level.INFO,
                    "the following user was successfully saved in the database" +
                            saveUser
            );
            context.status(201);
            context.json(saveUser);
        }
    }


    /**
     * Handle the login to login user in the app
     * @param context an {@link Context} object
     */
    public void loginUser(Context context) {
        String body = context.body();
        LoginData data = this.mapper.createLoginData(body);
        List<String> errors = validationService.loginData(data);

        if (!errors.isEmpty()) {
            this.errorController.withInvalidLoginData(context, errors);
            LOGGER.log(
                    Level.INFO,
                    "an attempt was made to create a user with the following" +
                            "invalid data: " + errors
            );
        }

        // TODO: 8/8/21 get the user from DB
        User user = this.userService.getUser(data.getEmail());

    }
}
