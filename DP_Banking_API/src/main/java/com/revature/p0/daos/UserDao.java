package com.revature.p0.daos;

import com.revature.p0.models.User;
import com.revature.p0.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class UserDao {
    Connection connection;

    public UserDao(Connection connection) throws SQLException, IOException, ClassNotFoundException {
        this.connection = connection;
    }

    // User registration
    public User registerUser(User user) throws SQLException {
        try {
            // Statement preparation
            String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            System.out.println("Exception: " + sqlException.getMessage() + "/n");
        }
        return user;
    }

    // User login

    // Create bank account

    // Account deposit

    // Account withdrawal

    // Account transfer

}
