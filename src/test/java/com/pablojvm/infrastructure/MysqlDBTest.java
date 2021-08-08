package com.pablojvm.infrastructure;

import com.pablojvm.user.User;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class MysqlDBTest {

    @Test
    @DisplayName("should return 0 when the user cannot be created")
    void errorEmail() {
        MysqlDB mysql = new MysqlDB();

        User user = new User("john", "carter",
                "example@example.com", "admin123"
        );

        int idUser = mysql.saveUser(user);

        assertEquals(0, idUser);
    }

    @Test
    void getUserWhenValidEmail() {
        MysqlDB mysql = new MysqlDB();
        String email = "test@example.com";
        User user = mysql.getUser(email);
    }
}