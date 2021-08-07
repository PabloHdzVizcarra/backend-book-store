package com.pablojvm.infrastructure;

import com.pablojvm.application.implDatabase;
import com.pablojvm.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MysqlDB implements implDatabase {
    private static final Logger LOGGER =
            Logger.getLogger(MysqlDB.class.getName());

    @Override
    public int saveUser(User user) {
        String query =
                "INSERT INTO user(user_name, user_lastname, user_email, " +
                        "user_password)" +
                        "VALUE (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/books",
                "root",
                "my-secret-pw"
        ); PreparedStatement userStatement = connection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS
        )) {
            userStatement.setString(1, user.getName());
            userStatement.setString(2, user.getLastname());
            userStatement.setString(3, user.getEmail());
            userStatement.setString(4, user.getPassword());

            userStatement.executeUpdate();
            int idUserCreated = 0;
            try (ResultSet resultSet = userStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    idUserCreated = resultSet.getInt(1);
                }
            }

            return idUserCreated;

        } catch (SQLException throwables) {
            LOGGER.log(
                    Level.WARNING,
                    "The following error occurred in the database: " +
                            throwables.getMessage()
            );
        }
        return 0;
    }
}
