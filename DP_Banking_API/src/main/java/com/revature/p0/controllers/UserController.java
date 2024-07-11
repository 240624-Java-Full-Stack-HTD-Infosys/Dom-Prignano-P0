package com.revature.p0.controllers;

import com.revature.p0.services.UserService;
import com.revature.p0.models.User;
import com.revature.p0.dtos.LoginDto;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List; // Needed for getAllUsers method.

import java.sql.SQLException;


public class UserController { // Overall, the controller receives requests from and returns responses to the client (like Postman).

    UserService userService; // Service is instantiated so its methods can be referenced.
    Javalin javalin; // Javalin object functions as a terminal, which clients or client simulations like Postman can connect to.

    public UserController(UserService userService, Javalin javalin) {

        // Above objects are instantiated in constructor.
        this.javalin = javalin;
        this.userService = userService;

        // Each request handler tells the javalin object what to do in the event of a certain type of request being made.
        javalin.post("/users", this::registerUser);
        javalin.get("/users", this::getAllUsers);
        javalin.post("/login", this::logUserIn);
        javalin.put("/users", this::updateUserInfo);

    }

    // User registration
    public User registerUser(Context ctx) throws SQLException {
        return userService.registerUser(ctx.bodyAsClass(User.class));

    }

    // Get all users
    public Context getAllUsers(Context ctx) {
        return ctx.json(userService.getAllUsers());
    }

    // User login
    public User logUserIn(Context ctx) throws SQLException {
        LoginDto loginDto = ctx.bodyAsClass(LoginDto.class); // CTX is a wrapper around request and response.
        return userService.logUserIn(loginDto.getUsername(), loginDto.getPassword());
        // Body is the payload - what is being sent or received
        // Converting to an existing class (login dto)
    }

    // Update user information
    public User updateUserInfo(Context ctx) throws SQLException {
        return userService.updateUserInfo(ctx.bodyAsClass(User.class));
    }
}