package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablojvm.user.LoginData;

public class JacksonImpl implements Mapper{
    private final ObjectMapper objectMapper;

    public JacksonImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public LoginData createLoginData(String body)  {
        try {
            return objectMapper.readValue(body, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
