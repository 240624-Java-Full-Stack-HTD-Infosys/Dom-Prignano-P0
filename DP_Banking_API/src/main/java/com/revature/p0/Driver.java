package com.revature.p0;

import com.revature.p0.utils.ServerUtil;

import java.io.IOException;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        ServerUtil.getServerUtil().initialize(8999);
    }
}