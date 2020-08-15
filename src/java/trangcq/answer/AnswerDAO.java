/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.answer;

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
public class AnswerDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AnswerDAO() {
        String.valueOf(65);
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

    public List<AnswerDTO> getAllAnswer() throws SQLException, NamingException {
        List<AnswerDTO> result = null;
        try {
            String sql = "Select Id, Content, QuestionId From Answer";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String content = rs.getString("Content");
                int questionId = rs.getInt("QuestionId");
                AnswerDTO dto = new AnswerDTO(id, content, questionId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<AnswerDTO> getAnswerOfQuestion(int questionId) throws SQLException, NamingException {
        AnswerDTO result = null;
        List<AnswerDTO> ansList = new ArrayList<>();
        try {
            String sql = "Select id, Content, QuestionId From Answer Where QuestionId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, questionId);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String content = rs.getString("Content");
                result = new AnswerDTO(id, content, questionId);
                ansList.add(result);
            }
        } finally {
            closeConnection();
        }
        return ansList;
    }

    public boolean deleteAnswer(int questionId) throws SQLException, NamingException {
        boolean result = false;

        try {
            String sql = "Delete FROM Answer where QuestionId=? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, questionId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateAnswer(AnswerDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "UPDATE [dbo].[Answer] "
                    + "   SET [Content] = ? "
                    + " WHERE id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getContent());
            preStm.setInt(2, dto.getId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public int insertAnswer(String content, int questionId) throws SQLException, NamingException {
        int answerId = -1;
        try {
            String sql = "INSERT INTO [dbo].[Answer] (Content, QuestionId) "
                    + "OUTPUT Inserted.Id "
                    + "VALUES(?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, content);
            preStm.setInt(2, questionId);
            rs = preStm.executeQuery();
            if(rs.next()){
                answerId = rs.getInt("Id");
            }
        } finally {
            closeConnection();
        }
        return answerId;
    }
}
