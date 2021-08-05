package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;
import com.pablojvm.domain.User;

public class UserPersistenceService
{
    public UserPersistenceService()
    {
    }

    // TODO: 8/5/21 create POJO User
    // TODO: 8/5/21 -- create class Password with hash
    // TODO: 8/5/21 hash password
    public void saveUser(DataPostUser data)
    {
        User userToSave = new User(
                data.getName(),
                data.getLastname(),
                data.getEmail(),
                data.getPassword()
        );


    }
}
