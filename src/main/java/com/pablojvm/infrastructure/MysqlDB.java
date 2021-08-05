package com.pablojvm.infrastructure;

import com.pablojvm.application.CrudOperationsDB;
import com.pablojvm.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
        ); PreparedStatement userStatement = connection.prepareStatement(query))
        {
            userStatement.setString(1, user.getName());
            userStatement.setString(2, user.getLastname());
            userStatement.setString(3, user.getEmail());
            userStatement.setString(4, user.getPassword());


            try (ResultSet resultSet = userStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    String userName = resultSet.getString("user_name");
                    System.out.println(userName);
                }
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }
}
