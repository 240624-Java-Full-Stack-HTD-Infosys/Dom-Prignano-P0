package com.revature.p0.services;

import com.revature.p0.daos.UserDao;
import com.revature.p0.models.User;

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

}
