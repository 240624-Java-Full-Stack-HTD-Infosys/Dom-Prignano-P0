package com.revature.p0.daos;

import com.revature.p0.models.User; // This allows the DAO to reference the User model's functionality, including its getters, setters,

import java.io.IOException;
import java.sql.*; // Convenient catch-all import that allows access to various useful JDBC functionalities needed for a DAO.
import java.util.ArrayList; // Needed for getAllUsers method.
import java.util.List; // Needed for getAllUsers method.

public class UserDao {

    // Class setup
    Connection connection;
    public UserDao(Connection connection) throws SQLException, IOException, ClassNotFoundException {
        this.connection = connection; // Constructor establishes connection object for classes that reference the DAO (Service).
    }

    // User registration
    public User registerUser(User user) {
        if (user.getUserId() == null) {
            try { // Try/catch block attempts to intercept SQLExceptions, which are common in JDBC.
                String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)"; // This string has most of the syntax of an SQL statement, and can be processed into one by the PSTMT.
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getFirstName()); // "?" wildcard in sql string
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
        }
        else { updateUserInfo(user); }
        return user;
    }

    // Update user information
    public User updateUserInfo(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, password = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            System.out.println("Exception: " + sqlException.getMessage() + "/n");
        }
        return user;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
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
        }
        catch (SQLException sqlException) {
            System.out.println("Exception: " + sqlException.getMessage() + "/n");
        }
        return allUsers;
    }

    // Get user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet resultSet = pstmt.executeQuery();
        User user = new User();
        if(resultSet.next()) {
            user.setUserId(resultSet.getInt("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    // Create bank account

    // Account deposit

    // Account withdrawal

    // Account transfer

}
