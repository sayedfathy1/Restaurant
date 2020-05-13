/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Administrator
 */
public class DBConnections 
{
    private static final String DB_URL ="jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER ="test";
    private static final String DB_PASSWORD ="test";
    
    private Connection conn;
    
    public DBConnections()
    {
        try 
        {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            System.out.println("DriverManager Registered !!");
             
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection is ready !!");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Connection Failed: " + ex.getMessage());
        }
    }

    /**
     * @return the conn
     */
    public Connection getConn() 
    {
        return conn;
    }

    
}
