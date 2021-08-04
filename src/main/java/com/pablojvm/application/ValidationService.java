package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidationService
{

    public ValidationService()
    {
    }

    // TODO: 8/4/21 validate password
    // TODO: 8/4/21 return array with errors or empty array
    public List<String> validateDataCreateUser(DataPostUser data)
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

        return list;
    }

    private String validateEmail(DataPostUser data)
    {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!Pattern.compile(regex).matcher(data.getEmail()).matches())
        {
            return "The email: " + data.getEmail() + " is not valid";
        }
        return null;
    }

    private String validateLastname(@NotNull DataPostUser data)
    {
        if (data.getLastname().length() < 4)
        {
            return "The lastname: " + data.getLastname() + " is not valid, " +
                    "should be length greater than 4";
        }

        return null;
    }

    private String validateName(@NotNull DataPostUser data)
    {
        if (data.getName().length() < 4)
        {
            return "The name: " + data.getName() + " is not valid, should be length " +
                    "greater than 4";
        }
        return null;
    }
}
