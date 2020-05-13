/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege;

import db.DBConnections;
import java.sql.*;
import java.util.*;
import users.UserBean;

/**
 *
 * @author Administrator
 */
public class PrivilegeHandler 
{
    Connection conn ;
    
    public PrivilegeHandler()
    {
        DBConnections dbconn = new DBConnections();
         conn = dbconn.getConn() ;
    }
    public Vector getAllPrivileges() throws SQLException
    {
        Vector privileges = new Vector();
        
        String sql = "select * from PRIVILEGE";
        Statement st = conn.createStatement();
        ResultSet rSet = st.executeQuery(sql);
        while(rSet.next())
        {
             PrivilegeBean pb = new PrivilegeBean();
             pb.setId(rSet.getInt("id"));
             pb.setName(rSet.getString("name"));

             privileges.addElement(pb);
        }
        rSet.close();
        st.close();
        
        return privileges;
    }
    public int addUserPrivilege(int userId, int privilegeId) throws SQLException
    {
        String sql = "insert into USER_PRIVILEGE (USER_ID , PRIVILEGE_ID)"
                + " values ("+userId+" , "+privilegeId+")";
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    
    public int updateUserPrivilege(UserBean selectedUB) throws SQLException
    {
        String sql = "update USER_PRIVILEGE "
                + " set PRIVILEGE_ID =" + selectedUB.getPrvId()
                + " where USER_ID ="+selectedUB.getId();
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    public int deleteUserPrivilege(UserBean selectedUB) throws SQLException
    {
        String sql = "delete USER_PRIVILEGE "
                + " where PRIVILEGE_ID =" + selectedUB.getPrvId()
                + " and USER_ID = "+selectedUB.getId();
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    public int deleteUserPrivilegeByUserId(int userId) throws SQLException
    {
        String sql = "delete USER_PRIVILEGE "
                + " where USER_ID = "+userId;
        
        Statement st = conn.createStatement();
        int done = st.executeUpdate(sql);
        conn.commit();
        st.close();
        return done;
    }
    /**
     * 
     * @param userId
     * @return
     * @throws SQLException 
     */
    public int getUserPrivilegeId(int userId) throws SQLException
    {
        int prvId = -1;
        String sql = "select PRIVILEGE_ID from USER_PRIVILEGE"
                + " where USER_ID ="+userId;
        
        Statement st = conn.createStatement();
        ResultSet rSet = st.executeQuery(sql);
        while(rSet.next())
        {
          prvId = rSet.getInt("PRIVILEGE_ID");
        }
        rSet.close();
        st.close();
        
        return prvId;
    }
    public void closeConnection() throws SQLException
    {
        conn.close();
    }
}
