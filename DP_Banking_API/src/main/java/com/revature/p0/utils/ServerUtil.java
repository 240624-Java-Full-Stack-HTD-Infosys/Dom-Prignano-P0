package com.revature.p0.utils; // Util class is stored in utils package.

import com.revature.p0.controllers.UserController; // Necessary to initialize controller layer.
import com.revature.p0.daos.UserDao; // Necessary to initialize DAO layer.
import com.revature.p0.services.UserService; // Necessary to initialize service layer.
import io.javalin.Javalin; // Necessary to create connections with client & database.

import java.io.*; // IO functionality is fundamental to server operation, and this is where it is implemented.
import java.sql.Connection; // Connection to database is necessary for SQL server interaction.
import java.sql.SQLException; // SQLException is an extremely common issue with JDBC, and so it must be handled.

public class ServerUtil {
    private static ServerUtil serverUtil; // Ensures serverUtil is encapsulated safely, only being accessible via its getter.

    private ServerUtil() { // Private no-args constructor for instantiation.
    }

    public static ServerUtil getServerUtil() { // Getter for use in the Driver.
        if(serverUtil == null) { // Only returns serverUtil functionality if one doesn't exist already, which saves memory.
            serverUtil = new ServerUtil(); // Instantiates serverUtil to be returned.
        }
        return serverUtil; // Return serverUtil (to driver)
    }

    public Javalin initialize(int port) throws SQLException, IOException, ClassNotFoundException { // Initialize functionality needed to run application at all.
        Javalin javalin = Javalin.create().start(port); // Javalin reserves a port to be used for client-application interaction.
        Connection connection = ConnectionUtil.getConnection(); // Initializes a connection object which can be used elsewhere to interface with the database.
        UserDao userDao = new UserDao(connection); // Initializes a DAO, which is essential in application operations.
        UserService userService = new UserService(userDao); // Initializes a DAO, which is essential in application operations.
        UserController userController = new UserController(userService, javalin); // Initializes a DAO, which is essential in application operations.
        return javalin; // Returns javalin object to be used throughout application.
    }
}
