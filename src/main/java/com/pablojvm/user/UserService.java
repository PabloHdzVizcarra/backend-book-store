package com.pablojvm.user;

import com.pablojvm.domain.DataUser;

/**
 * Service for user persistence in the app
 */
public interface UserService {

    /**
     * Save a user in the database
     *
     * @param data An {@link DataUser} object with user data
     * @return A {@link User} object what represents a user from the database
     */
    User saveUser(DataUser data);

    /**
     * Obtains a user from the assigned service.
     *
     * @param email of the user you are looking for
     * @return a {@link User} object represented from the database
     */
    User getUser(String email);

    /**
     * Deletes a user from the assigned storage service
     *
     * @param loginData represents a user credentials
     * @return true if the user is deleted, otherwise false
     */
    boolean deleteUser(LoginData loginData);
}
