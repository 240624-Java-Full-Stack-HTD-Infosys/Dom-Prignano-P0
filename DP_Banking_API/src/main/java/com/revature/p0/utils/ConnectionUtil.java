package com.revature.p0.utils;

import java.io.*;
import java.sql.*;
import java.util.Properties;


public class ConnectionUtil {

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));

        return connection;
    }
}