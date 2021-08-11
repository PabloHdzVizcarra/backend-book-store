package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;
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

    /**
     * Map data in JSON format to a {@link DataUser} object
     *
     * @param body A string in JSON format
     * @return A {@link DataUser} object with the assigned data
     * @throws IllegalArgumentException if any field is missing in the JSON
     */
    DataUser createDataUser(String body) throws IllegalArgumentException;
}
