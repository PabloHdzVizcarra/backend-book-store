package com.pablojvm.infrastructure;

import com.pablojvm.user.LoginData;

public interface Mapper {
    /**
     * Map data in JSON format to a {@link LoginData} object
     *
     * @param body A string in JSON format with data
     * @return A {@link LoginData} object with the assigned data
     * @throws IllegalArgumentException if any field is missing in the JSON
     */
    LoginData createLoginData(String body) throws IllegalArgumentException;
}
