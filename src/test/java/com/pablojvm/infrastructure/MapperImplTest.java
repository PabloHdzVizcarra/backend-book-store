package com.pablojvm.infrastructure;

import com.pablojvm.domain.DataUser;
import com.pablojvm.user.LoginData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.IOException;

@Testable
class MapperImplTest {
    @Test
    void shouldCreatePOJOCorrectly() throws IOException {
        MapperImpl mapper = new MapperImpl();
        String body = "{\n" +
                "  \"email\": \"test@example.com\",\n" +
                "  \"password\": \"admin123\"\n" +
                "}";

        LoginData data = mapper.createLoginData(body);

        Assertions.assertEquals("test@example.com", data.getEmail());
        Assertions.assertEquals("admin123", data.getPassword());
    }

    @Test
    void shouldReturnErrorWithInvalidData() {

    }
}