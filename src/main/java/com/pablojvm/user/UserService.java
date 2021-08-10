package com.pablojvm.user;

import com.pablojvm.application.PersistenceService;
import com.pablojvm.domain.DataUser;

public class UserService {
    private final PersistenceService persistenceService;

    public UserService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public User saveUser(DataUser data) {
        return this.persistenceService.saveUser(data);
    }

    public User getUser(String email) {
        return this.persistenceService.getUser(email);
    }

    public void deleteUser(LoginData loginData) {
        this.persistenceService.deleteUser(loginData);
    }
}
