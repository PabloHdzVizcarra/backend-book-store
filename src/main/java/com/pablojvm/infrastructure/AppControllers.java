package com.pablojvm.infrastructure;

import com.pablojvm.application.ValidationData;
import com.pablojvm.user.LoginData;
import com.pablojvm.user.User;
import com.pablojvm.user.UserPersistenceService;
import com.pablojvm.user.UserService;
import com.pablojvm.user.UserServiceImpl;

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
    private final Logger loggerService =
            Logger.getLogger(UserPersistenceService.class.getName());
    private final JwtService jwtService;

    public AppControllers(
            UserServiceImpl userService,
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
     */
    public void createUser(Context context) {
        RequestData requestData = new RequestData(
                context,
                mapper,
                validationService,
                loggerService,
                userService,
                jwtService
        );

        BodyDataMiddleware middleware = new BodyDataMiddleware();
        CheckDataMiddleware checkDataMiddleware = new CheckDataMiddleware();
        SaveUserMiddleware saveUserInDatabase = new SaveUserMiddleware();
        middleware
                .linkWith(checkDataMiddleware)
                .linkWith(saveUserInDatabase);

        middleware.check(requestData);
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
            loggerService.log(
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
            loggerService.log(Level.INFO, message);
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
            loggerService.log(Level.INFO, "try logging into server with missing cookie");
            this.errorResponse.withMessage(
                    context, "Missing cookie in the request, please check your request " +
                            "to server");
            return;
        }

        LoginData data = null;
        try {
            data = this.jwtService.validateCookie(cookie);
        } catch (IllegalArgumentException e) {
            loggerService.log(
                    Level.WARNING,
                    "An error occurred while validating the user" +
                            e.getMessage()
            );
        }

        if (data == null) {
            this.errorResponse.withMessage(context, "An error occurred while validating" +
                    " the user");
            return;
        }

        User user = this.userService.getUser(data.getEmail());
        this.validResponse.withDataUser(context, user);
        loggerService.log(Level.INFO, "The user: " + user + " logging in the app");
    }

    /**
     * Delete a user from the database if the user exists
     *
     * @param context a {@link Context} object
     */
    public void deleteUser(Context context) {
        String cookie = context.cookie("login");
        if (cookie == null) {
            loggerService.log(Level.INFO, "try logging into server with missing cookie");
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
            loggerService.log(Level.INFO, message);
            return;
        }

        if (!user.comparePassword(dataCookie.getPassword())) {
            this.errorResponse.withMessage(
                    context,
                    "the password: " + dataCookie.getPassword() +
                            " is not valid"
            );
            return;
        }

        boolean isDeleted = this.userService.deleteUser(dataCookie);
        if (!isDeleted) {
            this.errorResponse.withMessage(
                    context,
                    "a problem occurred when deleting the user from the database"
            );
            return;
        }

        this.validResponse.withMessage(
                context,
                "The user when the email: " + user.getEmail() +
                        " and id:  " + user.getId() + " is deleted from the database"
        );
    }
}
