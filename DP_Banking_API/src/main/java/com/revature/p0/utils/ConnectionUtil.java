package com.revature.p0.utils; // Util class is stored in utils package.

import java.io.*; // IO functionality is fundamental to server operation, and connection is among the most crucial components.
import java.sql.*; // SQL interaction is fundamental to this application's functionality.
import java.util.Properties; // Credentials from application.properties are needed to establish a connection with the database.


public class ConnectionUtil { // Used to establish a connection to the SQL database.

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException { // Connection getter to be used in ServerUtil.

        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties"); //Input stream sets up functionality for new
        Properties properties = new Properties(); // Properties object is instantiated to store application.properties contents for processing.
        properties.load(inputStream); // Prepares credentials to be sent to the database.

        Class.forName("org.postgresql.Driver"); // Initializes the Driver class to be connected to the SQL server as a liaison.
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password")); // Establishes connection with database using necessary URL and credentials.

        return connection; // Connection is returned for use in ServerUtil.
    }
}