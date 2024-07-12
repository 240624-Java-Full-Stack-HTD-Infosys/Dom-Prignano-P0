package com.revature.p0.daos; // DAO is stored in DAOs package.

import com.revature.p0.models.User; // This allows the DAO to reference the User model's functionality, including its getters, setters,

import java.sql.*; // Convenient catch-all import that allows access to various useful JDBC functionalities needed for a DAO.
import java.util.ArrayList; // Needed for getAllUsers method.
import java.util.List; // Needed for getAllUsers method.

public class UserDao { // Interacts directly with the SQL server via JDBC, performing a variety of CRUD operations.

    // Class setup
    Connection connection; // Instantiating connection object from ConnectionUtil for server connection use in the below methods.
    public UserDao(Connection connection) {
        this.connection = connection; // Constructor establishes connection object for classes that reference the DAO (Service).
    }

    // User registration
    public User registerUser(User user) {
        if (user.getUserId() == null) { // Only runs registration process if the user is actually new to the database.
            try { // Try/catch block attempts to intercept SQLExceptions in the below code.
                String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)"; // This string has most of the syntax of an SQL statement, and can be processed into one by the below PSTMT.
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Explain generated keys ***
                preparedStatement.setString(1, user.getFirstName()); // Sets first "?" wildcard in sql string to the first name parameter.
                preparedStatement.setString(2, user.getLastName()); // Sets second "?" wildcard in sql string to the last name parameter.
                preparedStatement.setString(3, user.getUsername()); // Sets third "?" wildcard in sql string to the username parameter.
                preparedStatement.setString(4, user.getPassword()); // Sets fourth "?" wildcard in sql string to the password parameter.
                preparedStatement.executeUpdate(); // Sends and executes prepared statement that was assembled above.
                ResultSet resultSet = preparedStatement.getGeneratedKeys(); // *** Explain result set
                if(resultSet.next()) { // The "next" method scans to see if there is a record to be added to the result set.
                    user.setUserId(resultSet.getInt(1)); // If there is a result, assign the user parameter's id to match it. ***
                }
            }
            catch (SQLException sqlException) { // Catch block triggers if an SQLException occurs.
                System.out.println("Exception: " + sqlException.getMessage() + "/n"); // Exception is handled by printing a relevant message to the console.
            }
        }
        else { updateUserInfo(user); } // Instead calls the user update method if said user already exists.
        return user; // Returns user object for use in service layer.
    }

    // Update user information
    public User updateUserInfo(User user) {
        try { // Try/catch block attempts to intercept SQLExceptions in the below code.
            String sql = "UPDATE users SET first_name = ?, last_name = ?, password = ? WHERE username = ?"; // This string has most of the syntax of an SQL statement, and can be processed into one by the below PSTMT.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName()); // Sets first "?" wildcard in sql string to the first name parameter.
            preparedStatement.setString(2, user.getLastName()); // Sets second "?" wildcard in sql string to the last name parameter.
            preparedStatement.setString(3, user.getPassword()); // Sets third "?" wildcard in sql string to the username parameter.
            preparedStatement.setString(4, user.getUsername()); // Sets fourth "?" wildcard in sql string to the password parameter.
            preparedStatement.executeUpdate(); // Sends and executes prepared statement that was assembled above.
        }
        catch (SQLException sqlException) { // Catch block triggers if an SQLException occurs.
            System.out.println("Exception: " + sqlException.getMessage() + "/n"); // Exception is handled by printing a relevant message to the console.
        }
        return user; // Returns user object for use in service layer.
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>(); // Establishes a list which will later contain all users, and be returned.
        try { // Try/catch block attempts to intercept SQLExceptions in the below code.
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(); // A result set (the contents of a table, generally from a query)
            while (resultSet.next()) { // Loop repeatedly adds records from table to allUsers until none remain.
                User user = new User(); // Establishes a new User object to be added to list with every iteration.
                user.setFirstName(resultSet.getString("first_name")); // Assigns first_name value from table to user instance.
                user.setLastName(resultSet.getString("last_name")); // Assigns last_name value from table to user instance.
                user.setUsername(resultSet.getString("username")); // Assigns username value from table to user instance.
                user.setPassword(resultSet.getString("password")); // Assigns password value from table to user instance.
                allUsers.add(user); // Adds new user instance to list.
            }
        }
        catch (SQLException sqlException) { // Catch block triggers if an SQLException occurs.
            System.out.println("Exception: " + sqlException.getMessage() + "/n"); // Exception is handled by printing a relevant message to the console.
        }
        return allUsers; // Returns list after assembly is complete.
    }

    // Get user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet resultSet = pstmt.executeQuery();
        User user = new User();
        if(resultSet.next()) {
            user.setUserId(resultSet.getInt("user_id"));  // Assigns user_id value from table to user instance.
            user.setFirstName(resultSet.getString("first_name")); // Assigns first_name value from table to user instance.
            user.setLastName(resultSet.getString("last_name")); // Assigns last_name value from table to user instance.
            user.setUsername(resultSet.getString("username")); // Assigns username value from table to user instance.
            user.setPassword(resultSet.getString("password")); // Assigns password value from table to user instance.
        }
        return user; // Returns user object for use in service layer.
    }
}
