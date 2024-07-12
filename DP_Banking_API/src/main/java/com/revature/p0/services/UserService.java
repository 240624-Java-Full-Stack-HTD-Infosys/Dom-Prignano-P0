package com.revature.p0.services; // Service is stored in services package.

import com.revature.p0.daos.UserDao; // Required to connect to and reference the DAO layer.
import com.revature.p0.models.User; // Required to reference

import java.util.List; // Needed for getAllUsers method.
import java.sql.SQLException; // Needed to throw possible login exceptions.

public class UserService { // Overall, the service contains business logic, and serves as an intermediary between

    // Class setup
    UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // User registration
    public User registerUser(User user) {
        return userDao.registerUser(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // User login
    public User logUserIn(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    // Update user information
    public User updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }
}
