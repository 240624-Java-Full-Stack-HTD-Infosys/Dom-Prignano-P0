package com.revature.p0; // Driver falls outside of standard package hierarchy, being on the same level as class-level packages.

import com.revature.p0.utils.ServerUtil; // Server utility is separated for conciseness, but is still essential and referenced.

import java.io.IOException; // IO exceptions are very common in this area of the project due to its role in interaction with outside sources. These issues must be addressed.
import java.sql.SQLException; // Required for potential issues when interacting with the database.

public class Driver { // Serves as a launchpad for the overall application.
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException { // Main method allows the program to be run.
        ServerUtil.getServerUtil().initialize(8999); // Calls the ServerUtil's initialize method, readying port 8999 to receive requests & send responses.
    }
}