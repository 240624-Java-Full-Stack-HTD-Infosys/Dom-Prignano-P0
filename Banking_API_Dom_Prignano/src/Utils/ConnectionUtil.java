package Utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(inputStream);

        Class.forName("org.postgresql.driver"); // Forces lazy class loader
        Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

        return conn;
    }
}
