package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.user.LoginData;

public class MapperImpl implements Mapper{
    private final ObjectMapper objectMapper;

    public MapperImpl() {
        this.objectMapper = new ObjectMapper();
    }

    public LoginData createLoginData(String body)  {
        try {
            return objectMapper.readValue(body, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
