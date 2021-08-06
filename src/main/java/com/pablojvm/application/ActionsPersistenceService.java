package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public interface ActionsPersistenceService {
    /**
     * Handles the saving of users in the database.
     *
     * @param data an {@link DataPostUser} with the correct values to create a user.
     * @return an {@link User} or null if the object could not be created.
     */
    User saveUser(DataPostUser data);
}
