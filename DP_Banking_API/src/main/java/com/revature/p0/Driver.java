package com.revature.p0;

import com.revature.p0.utils.ServerUtil; // Server utility is separated for conciseness, but is still essential and referenced.

import java.io.IOException;
import java.sql.SQLException;

public class Driver { // Serves as a launchpad for the overall application.
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException { // Main method allows the program to be run.
        ServerUtil.getServerUtil().initialize(8999); // Calls the ServerUtil's initialize method, readying port 8999 to receive requests & send responses.
    }
}