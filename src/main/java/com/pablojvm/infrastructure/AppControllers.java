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
    private final ResponseError errorResponse;
    private final ResponseOk validResponse;
    private final ValidationData validationService;
    private final Mapper mapper;
    private static final Logger LOGGER =
            Logger.getLogger(UserPersistenceService.class.getName());
    private final JwtService jwtService;

    public AppControllers(
            UserService userService,
            ValidationData validationService,
            Mapper mapper
    ) {
        this.userService = userService;
        this.validationService = validationService;
        this.mapper = mapper;
        this.errorResponse = new ResponseError();
        this.validResponse = new ResponseOk();
        this.jwtService = new JwtImpl();
    }

    /**
     * Handle logic to create one user in the app
     *
     * @param context An object {@link Context}
     * @throws JsonProcessingException some error
     */
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
            this.errorResponse.createUser(context, errorsList);
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
            this.errorResponse.withInvalidEmail(context);
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
     *
     * @param context an {@link Context} object
     */
    public void loginUser(Context context) {
        String body = context.body();
        LoginData dataRequest = this.mapper.createLoginData(body);
        List<String> errors = this.validationService.loginData(dataRequest);

        if (!errors.isEmpty()) {
            this.errorResponse.withInvalidLoginData(context, errors);
            LOGGER.log(
                    Level.INFO,
                    "an attempt was made to create a user with the following" +
                            "invalid data: " + errors
            );
            return;
        }

        User user = this.userService.getUser(dataRequest.getEmail());
        if (user == null) {
            String message = "The user with the email: " + dataRequest.getEmail() +
                    " is not exists in the database";
            this.errorResponse.withMessage(context, message);
            LOGGER.log(Level.INFO, message);
            return;
        }

        if (user.comparePassword(dataRequest.getPassword()))
            this.validResponse.withCookie(context, dataRequest);

    }

    /**
     * HTTP endpoint that handles application autologin
     *
     * @param context A {@link Context} object
     */
    public void autoLogin(Context context) {
        String cookie = context.cookie("login");

        if (cookie == null) {
            LOGGER.log(Level.INFO, "try logging into server with missing cookie");
            this.errorResponse.withMessage(
                    context, "Missing cookie in the request, please check your request " +
                            "to server");
            return;
        }

        LoginData data = null;
        try {
            data = this.jwtService.validateCookie(cookie);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "An error occurred while validating the user" +
                    e.getMessage());
        }

        if (data == null) {
            this.errorResponse.withMessage(context, "An error occurred while validating" +
                    " the user");
            return;
        }

        User user = this.userService.getUser(data.getEmail());
        this.validResponse.withDataUser(context, user);
        LOGGER.log(Level.INFO, "The user: " + user + " logging in the app");
    }

    /**
     * Delete a user from the database if the user exists
     *
     * @param context a {@link Context} object
     */
    public void deleteUser(Context context) {
        // TODO: 8/10/21 get user from database
        // TODO: 8/10/21 check passwords equals

        String cookie = context.cookie("login");
        if (cookie == null) {
            LOGGER.log(Level.INFO, "try logging into server with missing cookie");
            this.errorResponse.withMessage(
                    context, "Missing cookie in the request, please check your request " +
                            "to server");
            return;
        }

        LoginData dataCookie = null;
        try {
            dataCookie = this.jwtService.validateCookie(cookie);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (dataCookie == null || dataCookie.getEmail() == null) {
            this.errorResponse.withMessage(context, "An error occurred while validating" +
                    " the user");
            return;
        }

        User user = this.userService.getUser(dataCookie.getEmail());
        if (user == null) {
            String message = "The user with the email: " + dataCookie.getEmail() +
                    " is not exists in the database";
            this.errorResponse.withMessage(context, message);
            LOGGER.log(Level.INFO, message);
            return;
        }

        if (user.comparePassword(dataCookie.getPassword())) {
            this.validResponse.withCookie(context, dataCookie);
            return;
        }

        boolean isDeleted = this.userService.deleteUser(dataCookie);
    }
}
