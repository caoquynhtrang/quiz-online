/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import trangcq.conn.MyConnection;
import trangcq.role.RoleDTO;
import trangcq.status.StatusDTO;

/**
 *
 * @author USER
 */
public class UserDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public UserDAO() {
    }

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public UserDTO checkLogin(String email, String password) throws SQLException, NamingException {
        UserDTO result = null;
        try {
            String sql = "Select Email, U.[Name],[Password], Fullname  ,R.Id, R.Name AS RoleName "
                    + "From dbo.Users U JOIN Role R ON R.Id=U.RoleID Where Email = ? And Password = ? AND StatusID=1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String fullname = rs.getString("Fullname");
                int roleID=rs.getInt("Id");
                String roleName=rs.getString("RoleName");
                result = new UserDTO(email, name, fullname, new StatusDTO(1), new RoleDTO(roleID, roleName));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertAccount(String email, String password, String name, String fullname) throws SQLException, NamingException {
        try {
            String sql = "Insert Into Users(Email, Password, Name, Fullname, StatusId, RoleId) Values(?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            preStm.setString(3, name);
            preStm.setString(4, fullname);
            preStm.setInt(5, 1);
            preStm.setInt(6, 1);
            int row = preStm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
