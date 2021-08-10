package com.pablojvm.application;

import com.pablojvm.user.User;


public interface implDatabase {
    /**
     * Implementation to save a {@link User} in database.
     *
     * @param user a {@link User} with correct data
     * @return the id of user created in the database, otherwise it will return 0
     */
    int saveUser(User user);

    /**
     * Obtains a user from the database by means fo his email address
     *
     * @param email An email from a user registered in the database
     * @return An {@link User} or null
     */
    User getUser(String email);

    void deleteUser(String email);
}
