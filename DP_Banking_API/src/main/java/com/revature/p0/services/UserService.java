package com.revature.p0.services;

import com.revature.p0.daos.UserDao;
import com.revature.p0.models.User;

import java.util.List; // Needed for getAllUsers method.
import java.sql.SQLException;

public class UserService {

    // Class setup
    UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // User registration
    public User registerUser(User user) throws SQLException {
        return userDao.registerUser(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // User login
    public User logUserIn(String username, String password) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    // Update user information
    public User updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }
}
