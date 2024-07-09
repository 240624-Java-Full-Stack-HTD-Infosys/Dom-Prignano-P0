package com.revature.p0.controllers;

import com.revature.p0.services.UserService;
import com.revature.p0.models.User;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;


public class UserController {
    UserService userService;
    Javalin javalin;

    public UserController(UserService userService, Javalin javalin) {
        this.javalin = javalin;
        this.userService = userService;
    }

    public User registerUser(User user) throws SQLException {
        return userService.registerUser(user);
    }
}
