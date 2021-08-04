package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ValidationService
{

    public ValidationService()
    {
    }

    // TODO: 8/4/21 validate email
    // TODO: 8/4/21 validate password
    public List<String> validateDataCreateUser(DataPostUser data)
    {
        ArrayList<String> list = new ArrayList<>();

        String resultName = this.validateName(data);
        if (resultName != null)
            list.add(resultName);

        String resultLastname = this.validateLastname(data);
        if (resultLastname != null)
            list.add(resultLastname);

        return list;
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
