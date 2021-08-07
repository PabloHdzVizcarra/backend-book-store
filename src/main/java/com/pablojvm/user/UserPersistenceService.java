package com.pablojvm.user;

import com.pablojvm.application.ActionsPersistenceService;
import com.pablojvm.application.implDatabase;
import com.pablojvm.domain.LoginData;

public class UserPersistenceService implements ActionsPersistenceService
{
    private final implDatabase mysql;

    public UserPersistenceService(implDatabase mysql)
    {
        this.mysql = mysql;
    }

    @Override
    public User saveUser(LoginData data) throws IllegalArgumentException
    {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );

        int idUser = this.mysql.saveUser(userToSave);
        if (idUser == 0)
        {
            return null;
        }

        userToSave.setId(idUser);
        return userToSave;
    }
}
