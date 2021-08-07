package com.pablojvm.application;

import com.pablojvm.domain.LoginData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ValidationService
{

    public ValidationService()
    {
    }

    public List<String> validateDataCreateUser(LoginData data)
    {
        ArrayList<String> list = new ArrayList<>();

        String resultName = this.validateName(data);
        if (resultName != null)
            list.add(resultName);

        String resultLastname = this.validateLastname(data);
        if (resultLastname != null)
            list.add(resultLastname);

        String resultEmail = this.validateEmail(data);
        if (resultEmail != null)
            list.add(resultEmail);

        String resultPassword = this.validatePassword(data);
        if (resultPassword != null)
            list.add(resultPassword);

        return Collections.unmodifiableList(list);
    }

    private String validatePassword(@NotNull LoginData data)
    {
        if (data.getPassword().length() < 8)
            return "The password: " + data.getPassword() + " is not valid, " +
                    "should be length greater than 8";

        return null;
    }

    private String validateEmail(@NotNull LoginData data)
    {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!Pattern.compile(regex).matcher(data.getEmail()).matches())
        {
            return "The email: " + data.getEmail() + " is not valid";
        }
        return null;
    }

    private String validateLastname(@NotNull LoginData data)
    {
        if (data.getLastname().length() < 4)
        {
            return "The lastname: " + data.getLastname() + " is not valid, " +
                    "should be length greater than 4";
        }

        return null;
    }

    private String validateName(@NotNull LoginData data)
    {
        if (data.getName().length() < 4)
        {
            return "The name: " + data.getName() + " is not valid, should be length " +
                    "greater than 4";
        }
        return null;
    }

    public void validateLoginData() {

    }
}
