package com.pablojvm.infrastructure;

import com.pablojvm.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

@Disabled
class MysqlDBTest {
    MysqlDB mysql;

    @BeforeEach
    void beforeEach() {
        mysql = new MysqlDB();
    }

    @Test
    @DisplayName("should return 0 when the user cannot be created")
    void errorEmail() {
        User user = new User("john", "carter",
                "example@example.com", "admin123"
        );

        int idUser = mysql.saveUser(user);

        assertEquals(0, idUser);
    }

    @Test
    void getUserWhenValidEmail() {
        String email = "test@example.com";
        User user = mysql.getUser(email);

        assertNotNull(user.getId());
        assertNotNull(user.getName());
        assertNotNull(user.getLastname());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());
        assertEquals(email, user.getEmail());
    }

    @Test
    void whenInvalidEmail() {
        String emailInvalid = "error@email.com";
        User user = mysql.getUser(emailInvalid);

        assertNull(user);
    }

    @Test
    void whenNullEmail() {
        User user = mysql.getUser(null);

        assertNull(user);
    }
}