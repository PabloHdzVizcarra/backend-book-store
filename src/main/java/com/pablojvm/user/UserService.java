package com.pablojvm.user;

import com.pablojvm.application.ActionsPersistenceService;
import com.pablojvm.domain.DataPostUser;

public class UserService {
    private final ActionsPersistenceService persistenceService;

    public UserService(ActionsPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public User saveUser(DataPostUser data) {
        return this.persistenceService.saveUser(data);
    }
}
