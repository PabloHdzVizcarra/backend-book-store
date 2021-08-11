package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;
import com.pablojvm.user.User;
import com.pablojvm.user.UserService;

public class SaveUserInDatabase extends Middleware{
    @Override
    public boolean check(RequestData requestData) {
        UserService userService = requestData.getUserService();
        DataUser data = requestData.getDataUser();
        User userFromDatabase = userService.saveUser(data);

        if (userFromDatabase == null) {
            System.out.println("dont save the user");
        }
        return checkNext(requestData);
    }
}
