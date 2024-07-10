package com.revature.p0.controllers;

import com.revature.p0.services.UserService;
import com.revature.p0.models.User;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Cookie;

import java.sql.SQLException;


public class UserController {
    UserService userService;
    Javalin javalin;



    public UserController(UserService userService, Javalin javalin) {
        this.javalin = javalin;
        this.userService = userService;

        javalin.post("/users", this::registerUser);

    }

    // User registration
    public User registerUser(Context ctx) throws SQLException {
        return userService.registerUser(ctx.bodyAsClass(User.class));

    }
}
