package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.user.LoginData;

public class MapperImpl {
    private final ObjectMapper objectMapper;

    public MapperImpl() {
        this.objectMapper = new ObjectMapper();
    }

    public LoginData createLoginData(String body) throws JsonProcessingException {
        return objectMapper.readValue(body, new TypeReference<>() {
        });
    }
}
