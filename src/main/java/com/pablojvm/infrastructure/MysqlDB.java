package com.pablojvm.infrastructure;

import com.pablojvm.application.implDatabase;
import com.pablojvm.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MysqlDB implements implDatabase {
    private static final Logger LOGGER =
            Logger.getLogger(MysqlDB.class.getName());

    private final String URL =
            "jdbc:mysql://localhost:3306/books";
    private final String USER = "root";
    private final String PASS = "my-secret-pw";

    @Override
    public int saveUser(User user) {
        String query =
                "INSERT INTO user(user_name, user_lastname, user_email, " +
                        "user_password)" +
                        "VALUE (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement userStatement = connection.prepareStatement(
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

    @Override
    public Optional<User> getUser(String email) {
        String query =
                "SELECT user_id, user_name, user_lastname, user_email, user_password " +
                        "FROM user WHERE user_email=?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.execute();
            User user = null;

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("user_id"));
                    String name = resultSet.getString("user_name");
                    String lastname = resultSet.getString("user_lastname");
                    String userEmail = resultSet.getString("user_email");
                    String password = resultSet.getString("user_password");

                    user = new User(id, name, lastname, userEmail, password);
                }
            }

            if (user != null)
                return Optional.of(user);

            return Optional.empty();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
