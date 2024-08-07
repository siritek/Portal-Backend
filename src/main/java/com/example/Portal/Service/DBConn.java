package com.example.Portal.Service;


import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
@Service
public class DBConn {
    public static Connection getMyConnection() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/portal_db", "root", "root");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return conn;
    }
}
