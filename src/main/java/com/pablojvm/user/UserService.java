package com.pablojvm.user;

import com.pablojvm.application.ActionsPersistenceService;
import com.pablojvm.domain.DataUser;

public class UserService {
    private final ActionsPersistenceService persistenceService;

    public UserService(ActionsPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public User saveUser(DataUser data) {
        return this.persistenceService.saveUser(data);
    }

    public User getUser(String email) {
        return this.persistenceService.getUser(email);
    }
}
