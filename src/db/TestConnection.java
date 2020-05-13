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
public class TestConnection 
{
    private DBConnections dbconn = new DBConnections();
    String sql;
    
    public void insert()
    {
        Connection conn = dbconn.getConn();
        
        sql = "insert into USERS(name , password)"
                + " VALUES ('new22' , '123')"
                ;
        try 
        {
            Statement st =  conn.createStatement();
            int x = st.executeUpdate(sql);
            
            conn.commit();
        } 
        catch (SQLException ex) 
        {
           System.out.println("insert Exp: >> " + ex.getMessage());
        }
//        finally
//        {
//            try 
//            {
//                conn.close();
//            }
//            catch (SQLException ex) 
//            {
//               System.out.println("Close Exp: >> " + ex.getMessage());
//            }
//        }
        
    }
    public void delete()
    {
        Connection conn = dbconn.getConn();
        
        sql = "delete USERS where id = 21"
                ;
        try 
        {
            Statement st =  conn.createStatement();
            int x = st.executeUpdate(sql);
            
            conn.commit();
        } 
        catch (SQLException ex) 
        {
           System.out.println("update Exp: >> " + ex.getMessage());
        }
    }
    public void update()
    {
        Connection conn = dbconn.getConn();
        
        sql = "update USERS set name ='abc' , password= '123'"
                + " where id = 21"
                ;
        
        
        try 
        {
            Statement st =  conn.createStatement();
            int x = st.executeUpdate(sql);
            
            conn.commit();
        } 
        catch (SQLException ex) 
        {
           System.out.println("update Exp: >> " + ex.getMessage());
        }
    }
    public void select()
    {
        Connection conn = dbconn.getConn();
        sql = "select * from USERS"
               // + " where name = 'test'"
                ;
        try 
        {
            Statement st =  conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String pass = rs.getString("password");
                
                 System.out.println("Id=" + id + ", name=" + name + ", password="+ pass);
            }
        } 
        
        catch (SQLException ex) 
        {
            System.out.println("Select Exp: >> " + ex.getMessage());
        }
//        finally
//        {
//            try 
//            {
//                conn.close();
//            }
//            catch (SQLException ex) 
//            {
//               System.out.println("Close Exp: >> " + ex.getMessage());
//            }
//        }
    }
}
