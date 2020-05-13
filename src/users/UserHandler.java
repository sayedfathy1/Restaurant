/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import db.DBConnections;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class UserHandler 
{
    Connection conn ;
    
    public UserHandler ()
    {
         DBConnections dbconn = new DBConnections();
         conn = dbconn.getConn() ;
    }
    /**
     * 
     * @param userId
     * @return 
     */
    public int deleteUserById(int userId) throws SQLException
    {
        String sql = "delete USERS where ID="+ userId ;
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    public int updateUser(UserBean selectedUB) throws SQLException
    {
        String sql = "update USERS "
                + " set NAME='"+selectedUB.getName()+"' ,"
                + " PASSWORD='"+selectedUB.getPassword()+"' "
                + " where ID="+ selectedUB.getId() ;
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    public int checkUser(String user, String password) throws SQLException
    {
        int validID = -1;
        String sql = "select id from USERS"
                + " where NAME='"+user+"' "
                + "and PASSWORD='"+password+"'"
                ;
       Statement st = conn.createStatement();
       ResultSet rSet = st.executeQuery(sql);
       while(rSet.next())
       {
         validID = rSet.getInt("id");
       }
       rSet.close();
       st.close();
       
       return validID;
    } 
    
    public int getUserPrivilege(int id) throws SQLException
    {
        int userPriv = -1;
        String sql = "select PRIVILEGE_ID from USER_PRIVILEGE"
                + " where USER_ID = "+id
                ;
       Statement st = conn.createStatement();
       ResultSet rSet = st.executeQuery(sql);
       while(rSet.next())
       {
         userPriv = rSet.getInt("PRIVILEGE_ID");
       }
       rSet.close();
       st.close();
       
       return userPriv;
    }
    public int addUser(String name, String password) throws SQLException
    {
        String sql = "insert into USERS (NAME , PASSWORD)"
                + " values ('"+name+"' ,'"+password+"')";
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    
    public Vector getAllUsers() throws SQLException
    {
       Vector dataSet = new Vector();
       String sql = "select * from USERS order by NAME";
       Statement st = conn.createStatement();
       ResultSet rSet = st.executeQuery(sql);
       while(rSet.next())
       {
            UserBean ub = new UserBean();
            ub.setId(rSet.getInt("id"));
            ub.setName(rSet.getString("name"));
            ub.setPassword(rSet.getString("password"));
         
            dataSet.addElement(ub);
       }
       rSet.close();
       st.close();
       
       return dataSet;
    }
    public void closeConnection() throws SQLException
    {
        conn.close();
    }
}
