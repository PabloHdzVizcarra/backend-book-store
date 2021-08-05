package com.pablojvm.infrastructure;

import com.pablojvm.application.CrudOperationsDB;
import com.pablojvm.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MysqlDB implements CrudOperationsDB
{

    @Override
    public User saveUser(User user)
    {
        String query =
                "INSERT INTO user(user_name, user_lastname, user_email, " +
                        "user_password)" +
                        "VALUE (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/books",
                "root",
                "my-secret-pw"
        ); PreparedStatement userStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS))
        {
            userStatement.setString(1, user.getName());
            userStatement.setString(2, user.getLastname());
            userStatement.setString(3, user.getEmail());
            userStatement.setString(4, user.getPassword());

            userStatement.executeUpdate();
            try (ResultSet resultSet = userStatement.getGeneratedKeys())
            {
                while (resultSet.next())
                {
                    System.out.println(resultSet.getInt(1));
                }
            }


        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }
}
