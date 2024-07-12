package com.revature.p0.controllers; // Controller is stored in controllers package.

import com.revature.p0.services.UserService; // // Required to connect to and reference the Service layer.
import com.revature.p0.models.User;  // Required to reference user model's various getters and setters, and object instantiation.
import com.revature.p0.dtos.LoginDto; // Login DTO is needed for login functionality.

import io.javalin.Javalin; // With JDBC, Javalin is needed for client connections to be made.
import io.javalin.http.Context; // Context objects allow for efficient data transfer across the aforementioned connections.
import java.sql.SQLException; // Required for login exception handling which couldn't be handled elsewhere.


public class UserController { // Overall, the controller receives requests from and returns responses to the client (like Postman).

    UserService userService; // Service is instantiated so its methods can be referenced.
    Javalin javalin; // Javalin object functions as a terminal, which clients or client simulations like Postman can connect to.

    public UserController(UserService userService, Javalin javalin) { // Controller constructor provides instantiation for important objects, which are needed for client operation.

        // Above objects are instantiated in constructor.
        this.javalin = javalin;
        this.userService = userService;

        // Each request handler tells the javalin object what to do in the event of a certain type of request being made.
        javalin.post("/users", this::registerUser); // If a post request targets /users, run registerUser.
        javalin.get("/users", this::getAllUsers); // If a get request targets /users, run getAllUsers.
        javalin.post("/login", this::logUserIn); // If a post request targets /login, run logUserIn.
        javalin.put("/users", this::updateUserInfo); // If a put request targets /users, run updateUserInfo.

    }

    // User registration
    public User registerUser(Context ctx) { // The context object serves as a wrapper for request and response information. It is an intermediary form between client and application.
        return userService.registerUser(ctx.bodyAsClass(User.class)); // The context obj is passed as a parameter. bodyAsClass allows makes its parameter (body) contents comprehensible to the application.
    }

    // Get all users
    public Context getAllUsers(Context ctx) { // The context object serves as a wrapper for request and response information.
        return ctx.json(userService.getAllUsers()); // The list of users is returned in the form of json information inside a context object, which is readable to the client.
    }

    // User login
    public User logUserIn(Context ctx) throws SQLException { // SQLException is finally thrown to the JVM.
        LoginDto loginDto = ctx.bodyAsClass(LoginDto.class); // LoginDto is a database-adjacent model containing the needed parameters for login.
        return userService.logUserIn(loginDto.getUsername()); // Converting an instance of LoginDto to Context lets it effectively parameterize logUserIn.
    }

    // Update user information
    public User updateUserInfo(Context ctx) { // The context object serves as a wrapper for request and response information. It is an intermediary form between client and application.
        return userService.updateUserInfo(ctx.bodyAsClass(User.class)); // The context obj is passed as a parameter. bodyAsClass allows makes its parameter (body) contents comprehensible to the application.
    }
}