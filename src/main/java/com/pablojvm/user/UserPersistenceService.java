package com.pablojvm.user;

import com.pablojvm.application.PersistenceService;
import com.pablojvm.application.implDatabase;
import com.pablojvm.domain.DataUser;

public class UserPersistenceService implements PersistenceService {
    private final implDatabase mysql;

    public UserPersistenceService(implDatabase mysql) {
        this.mysql = mysql;
    }

    @Override
    public User saveUser(DataUser data) throws IllegalArgumentException {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );

        int idUser = this.mysql.saveUser(userToSave);
        if (idUser == 0) {
            return null;
        }

        userToSave.setId(idUser);
        return userToSave;
    }

    @Override
    public User getUser(String email) {
        return this.mysql.getUser(email);
    }

    @Override
    public boolean deleteUser(LoginData loginData) {
        return this.mysql.deleteUser(loginData.getEmail());
    }
}
