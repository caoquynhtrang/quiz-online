/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trangcq.conn.MyConnection;

/**
 *
 * @author USER
 */
public class SubjectDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public SubjectDAO() {
    }

    public void closeConnection() throws SQLException, NamingException {
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

    public List<SubjectDTO> getAllSubject() throws SQLException, NamingException {
        List<SubjectDTO> result = null;
        try {
            String sql = "Select Id, Code, Name, QuestionCount, Duration From Subject";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                int questionCount = rs.getInt("QuestionCount");
                int duration = rs.getInt("Duration");
                SubjectDTO dto = new SubjectDTO(id, questionCount, duration, code, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<SubjectDTO> searchSubject(String subject) throws SQLException, NamingException {
        List<SubjectDTO> result = null;
        try {
            String sql = "Select Id, Code, Name, QuestionCount, Duration From Subject Where Id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, Integer.parseInt(subject));
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                int questionCount = rs.getInt("QuestionCount");
                int duration = rs.getInt("Duration");
                SubjectDTO dto = new SubjectDTO(id, questionCount, duration, code, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public SubjectDTO getSubjectById(int subjectId) throws SQLException, NamingException {
        SubjectDTO result = null;
        try {
            String sql = "SELECT [Id] "
                    + "      ,[Code] "
                    + "      ,[Name] "
                    + "      ,[QuestionCount] "
                    + "      ,[Duration] "
                    + "  FROM [dbo].[Subject] "
                    + "  Where Id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, subjectId);
            rs = preStm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("Id");
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                int questionCount = rs.getInt("QuestionCount");
                int duration = rs.getInt("Duration");
                result = new SubjectDTO(id, questionCount, duration, code, name);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    
}
