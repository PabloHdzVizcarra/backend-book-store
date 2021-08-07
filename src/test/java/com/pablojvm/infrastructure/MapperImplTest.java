package com.pablojvm.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pablojvm.user.LoginData;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Testable
class MapperImplTest {
    @Test
    void shouldCreatePOJOCorrectly() throws IOException {
        JacksonImpl mapper = new JacksonImpl();
        String body = "{\n" +
                "  \"email\": \"test@example.com\",\n" +
                "  \"password\": \"admin123\"\n" +
                "}";

        LoginData data = mapper.createLoginData(body);

        assertEquals("test@example.com", data.getEmail());
        assertEquals("admin123", data.getPassword());
    }

    @Test
    void shouldReturnErrorWithInvalidData() {
        JacksonImpl mapper = new JacksonImpl();
        String body = "{\n" +
                "  \"\": \"\",\n" +
                "  \"\": \"\"\n" +
                "}";

        assertThrows(JsonProcessingException.class, () ->
        mapper.createLoginData(body));
    }
}