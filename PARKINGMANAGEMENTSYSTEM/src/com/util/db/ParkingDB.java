package com.util.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ParkingDB {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost/PARKINGMANAGEMENTSYSTEM", 
            "root", 
            ""
        );
    }
}