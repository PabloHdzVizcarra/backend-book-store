package com.pablojvm.infrastructure;

import com.pablojvm.domain.User;

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
}