package com.pablojvm.application;

import com.pablojvm.domain.DataPostUser;

import java.util.ArrayList;
import java.util.List;

public class ValidationService
{

    public ValidationService()
    {
    }

    // TODO: 8/4/21 validate field name
    // TODO: 8/4/21 validate field lastname
    public List<String> validateDataCreateUser(DataPostUser data)
    {
        ArrayList<String> list = new ArrayList<>();

        if (data.getName().length() < 4)
        {
            list.add("The name: " + data.getName() + " is not valid, should be length " +
                    "greater than 4");
        }

        return list;
    }
}
