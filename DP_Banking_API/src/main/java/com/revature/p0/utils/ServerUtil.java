package com.revature.p0.utils; // Util class is stored in utils package.

import com.revature.p0.controllers.UserController;
import com.revature.p0.daos.UserDao;
import com.revature.p0.services.UserService;
import io.javalin.Javalin;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerUtil {
    private static ServerUtil serverUtil;

    private ServerUtil() {
    }

    public static ServerUtil getServerUtil() {
        if(serverUtil == null) {
            serverUtil = new ServerUtil();
        }
        return serverUtil;
    }

    public Javalin initialize(int port) throws SQLException, IOException, ClassNotFoundException {
        Javalin api = Javalin.create().start(port);
        Connection conn = ConnectionUtil.getConnection();
        UserDao userDao = new UserDao(conn);
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService, api);
        return api;
    }
}
