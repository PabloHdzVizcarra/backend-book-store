package com.pablojvm.user;

import com.pablojvm.application.PersistenceService;
import com.pablojvm.domain.DataUser;

public class UserServiceImpl implements UserService{
    private final PersistenceService persistenceService;

    public UserServiceImpl(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @Override
    public User saveUser(DataUser data) {
        return this.persistenceService.saveUser(data);
    }

    @Override
    public User getUser(String email) {
        return this.persistenceService.getUser(email);
    }

    @Override
    public boolean deleteUser(LoginData loginData) {
        return this.persistenceService.deleteUser(loginData);
    }
}
