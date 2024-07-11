package com.revature.p0.controllers;

import com.revature.p0.services.UserService;
import com.revature.p0.models.User;
import com.revature.p0.dtos.LoginDto;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List; // Needed for getAllUsers method.

import java.sql.SQLException;


public class UserController { // Overall, the controller receives requests from and returns responses to the client (like Postman).
    UserService userService;
    Javalin javalin;

// Postman is part of client that doesn't exist - it simulates.

    public UserController(UserService userService, Javalin javalin) {
        this.javalin = javalin; // Serves as something Postman can connect to
        this.userService = userService;

        // Request handlers
        javalin.post("/users", this::registerUser);
        javalin.get("/users", this::getAllUsers);
        javalin.post("/login", this::logUserIn); // Telling Javalin what to do when the event occurs
        javalin.put("/users", this::updateUserInfo);



    }

    // User registration
    public User registerUser(Context ctx) throws SQLException {
        return userService.registerUser(ctx.bodyAsClass(User.class));

    }

    // Get all users
    public List<User> getAllUsers(Context ctx) throws SQLException {
        return userService.getAllUsers(ctx.bodyAsClass(User.class));
    }

    // User login
    public User logUserIn(Context ctx) throws SQLException {
        LoginDto loginDto = ctx.bodyAsClass(LoginDto.class); // CTX is a wrapper around request and repsonse.
        return userService.logUserIn(loginDto.getUsername(), loginDto.getPassword());
        // Body is the payload - what is being sent or received
        // Converting to an existing class (login dto)
    }

    // Update user information
    public User updateUserInfo(Context ctx) throws SQLException {
        return userService.updateUserInfo(ctx.bodyAsClass(User.class));
    }
}