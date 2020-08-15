/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author USER
 */
public class MyConnection implements Serializable{
    public static Connection getMyConnection() throws SQLException, NamingException{
//        Context context = new InitialContext();
//        Context tomContext = (Context) context.lookup("java:comp/env");
//        DataSource ds = (DataSource) tomContext.lookup("SE1404");
//        Connection conn = ds.getConnection();
        String dbURL = "jdbc:sqlserver://den1.mssql7.gear.host;databaseName=lab1database";
        String user = "lab1database";
        String pass = "Qv02Mbm6_!e4";
        Connection conn = DriverManager.getConnection(dbURL, user, pass);
        return conn;
    }
}
