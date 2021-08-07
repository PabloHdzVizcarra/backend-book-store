package com.pablojvm.application;

import com.pablojvm.domain.DataUser;
import com.pablojvm.user.User;

public interface ActionsPersistenceService {
    /**
     * Handles the saving of users in the database.
     *
     * @param data an {@link DataUser} with the correct values to create a user.
     * @return an {@link User} or null if the object could not be created.
     */
    User saveUser(DataUser data);
}
