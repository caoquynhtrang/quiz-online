/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.quiz;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trangcq.conn.MyConnection;

/**
 *
 * @author USER
 */
public class QuizDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuizDAO() {
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

    public List<QuizDTO> getAllQuiz() throws SQLException, NamingException {
        List<QuizDTO> result = null;
        try {
            String sql = "SELECT [Id] "
                    + "      ,[CreateDate] "
                    + "      ,[SubjectId] "
                    + "      ,[Email] "
                    + "  FROM [dbo].[Quiz]";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                String email = rs.getString("Email");
                QuizDTO dto = new QuizDTO(id, createDate, subjectId, email);
                result.add(dto);
            }

        } finally {
            closeConnection();
        }
        return result;
    }

    public int insertQuiz(QuizDTO dto) throws SQLException, NamingException {
        int quizId = -1;
        try {
            String sql = "INSERT INTO [dbo].[Quiz] "
                    + "           ([CreateDate] "
                    + "           ,[SubjectId] "
                    + "           ,[Email]) "
                    + " OUTPUT Inserted.Id "
                    + "     VALUES(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, dto.getCreateDate());
            preStm.setInt(2, dto.getSubjectId());
            preStm.setString(3, dto.getEmail());
            rs = preStm.executeQuery();
            if (rs.next()) {
                quizId = rs.getInt("Id");
            }
        } finally {
            closeConnection();
        }
        return quizId;
    }

    public List<QuizDTO> getQuizByEmail(String email) throws SQLException, NamingException {
        List<QuizDTO> result = null;
        try {
            String sql = "SELECT [Id] "
                    + "      ,[CreateDate] "
                    + "      ,[SubjectId] "
                    + "      ,[Email] "
                    + "  FROM [dbo].[Quiz] "
                    + "  Where Email like ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + email + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                email = rs.getString("Email");
                QuizDTO dto = new QuizDTO(id, createDate, subjectId, email);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public QuizDTO getQuizById(int id) throws SQLException, NamingException {
        QuizDTO result = null;
        try {
            String sql = "SELECT [Id] "
                    + "      ,[CreateDate] "
                    + "      ,[SubjectId] "
                    + "      ,[Email] "
                    + "  FROM [dbo].[Quiz] "
                    + "  Where Id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                String email = rs.getString("Email");
                result = new QuizDTO(id, createDate, subjectId, email);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
