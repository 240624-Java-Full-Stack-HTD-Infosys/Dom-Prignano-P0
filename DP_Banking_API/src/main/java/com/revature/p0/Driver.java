package com.revature.p0;

import com.revature.p0.utils.ServerUtil;

import java.io.IOException;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException { // Main method allows the program to be run.
        ServerUtil.getServerUtil().initialize(8999); // Calls the ServerUtil's initialize method, readying port 8999 for receiving requests & sending responses.
    }
}