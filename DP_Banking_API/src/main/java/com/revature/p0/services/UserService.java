package com.revature.p0.services; // Service is stored in services package.

import com.revature.p0.daos.UserDao; // Required to connect to and reference the DAO layer.
import com.revature.p0.models.User; // Required to reference user model's various getters and setters, and object instantiation.

import java.util.List; // Needed for getAllUsers method.
import java.sql.SQLException; // Needed to throw possible login exceptions.

public class UserService { // Overall, the Service layer contains business logic, and serves as an intermediary between the DAO and Controller layers.

    // Class setup
    UserDao userDao; // DAO is instantiated so its methods can be referenced.
    public UserService(UserDao userDao) { // Service constructor provides instantiation for Controller layer.
        this.userDao = userDao; // DAO is instantiated for use.
    }

    // User registration
    public User registerUser(User user) { // User parameter is passed in to fulfill its instantiation.
        return userDao.registerUser(user); // Calls the corresponding method from the DAO to process through business logic.
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDao.getAllUsers(); // Calls the corresponding method from the DAO to process through business logic.
    }

    // User login
    public User logUserIn(String username) throws SQLException { // Username parameter is passed in to fulfill its instantiation.
        return userDao.getUserByUsername(username); // Calls basic getUserByUsername method to process through business logic.
    }

    // Update user information
    public User updateUserInfo(User user) { // User parameter is passed in to fulfill its instantiation.
        return userDao.updateUserInfo(user); // Calls the corresponding method from the DAO to process through business logic.
    }
}
