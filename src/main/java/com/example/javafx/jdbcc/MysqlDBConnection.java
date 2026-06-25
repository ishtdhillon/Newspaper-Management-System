package com.example.javafx.jdbcc;

import java.sql.*;

public class MysqlDBConnection
{
    public static Connection getMysqlDBConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/newspaperjava","root","JIMMY17dhillon");

        }
        catch(Exception exp)
        {
            System.out.println(exp.toString());
        }
        return con;

    }
}
