package com.openbook.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBaseConn {
	 private static final String URL = "jdbc:mysql://localhost:3306/openbook1";
	 private static final String USERNAME = "root";
	 private static final String PASSWORD = "#@!Roki8653";
    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws SQLException {

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
