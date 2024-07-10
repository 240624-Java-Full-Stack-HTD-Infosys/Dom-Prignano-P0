package com.revature.p0.daos;

import com.revature.p0.models.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection;

    public UserDao(Connection connection) throws SQLException, IOException, ClassNotFoundException {
        this.connection = connection;
    }

    // User registration
    public User registerUser(User user) throws SQLException {
        try {
            String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
            }
        }
        catch (SQLException sqlException) {
            System.out.println("Exception: " + sqlException.getMessage() + "/n");
        }
        return user;
    }

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            allUsers.add(user);
        }
        return allUsers;
    }

    // User login

    // Create bank account

    // Account deposit

    // Account withdrawal

    // Account transfer

}
