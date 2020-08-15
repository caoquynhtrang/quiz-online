/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.question;

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
public class QuestionDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuestionDAO() {
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

    public int countTotalQuestion() throws SQLException, NamingException {
        int count = 0;
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement("SELECT COUNT(Id) as totalRows from Question");
            rs = preStm.executeQuery();

            if (rs.next()) {
                count = rs.getInt("totalRows");
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<QuestionDTO> getQuestionPaging() throws SQLException, NamingException {
        return getQuestionPaging(1);
    }

    public List<QuestionDTO> getQuestionPaging(int pageNumber) throws SQLException, NamingException {
        int pageSize = 5;
        List<QuestionDTO> result = null;
        try {
            String sql = "Select Id, Content, CreateDate, StatusId, SubjectId, CorrectAnswerId "
                    + "From Question "
                    + "Where StatusId = ? "
                    + "ORDER BY Id "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 1);
            preStm.setInt(2, pageSize * (pageNumber - 1));
            preStm.setInt(3, pageSize);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String content = rs.getString("Content");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int id = rs.getInt("Id");
                int statusId = rs.getInt("StatusId");
                int subjectId = rs.getInt("SubjectId");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, statusId, subjectId, correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuestionDTO> getAllQuestion() throws SQLException, NamingException {
        List<QuestionDTO> result = null;
        try {
            String sql = "Select Id, Content, CreateDate, StatusId, SubjectId, CorrectAnswerId From Question Where StatusId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 1);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String content = rs.getString("Content");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int id = rs.getInt("Id");
                int statusId = rs.getInt("StatusId");
                int subjectId = rs.getInt("SubjectId");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, statusId, subjectId, correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int insertQuestion(QuestionDTO dto) throws SQLException, NamingException {
        int questionId = -1;
        try {
            String sql = "INSERT INTO [dbo].[Question] (Content, StatusId, SubjectId, CreateDate) "
                    + "OUTPUT Inserted.Id "
                    + "VALUES(?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getContent());
            preStm.setInt(2, 1);
            preStm.setInt(3, dto.getSubjectId());
            preStm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            rs = preStm.executeQuery();
            if (rs.next()) {
                questionId = rs.getInt("Id");
            }
        } finally {
            closeConnection();
        }
        return questionId;
    }

//    public boolean deleteQuestion(int id) throws SQLException, NamingException {
//        boolean result = false;
//        try {
//            String sql = "Delete From Question Where id = ? ";
//            conn = MyConnection.getMyConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setInt(1, id);
//            result = preStm.executeUpdate() > 0;
//        } finally {
//            closeConnection();
//        }
//        return result;
//    }
    public boolean updateQuestion(QuestionDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "UPDATE [dbo].[Question] "
                    + "   SET [Content] = ? "
                                        + "      ,[CorrectAnswerId] = ? "
                    //                    + "      ,[CreateDate] = ? "
                    //                    + "      ,[SubjectId] = ? "
                    //                    + "      ,[StatusId] = ? "
                    + " WHERE id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getContent());
            preStm.setInt(2, dto.getCorrectAnswerId());
//            preStm.setDate(3, dto.getCreateDate());
//            preStm.setInt(4, dto.getSubjectId());
//            preStm.setInt(5, dto.getStatusId());
            preStm.setInt(3, dto.getId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteQuestions(int id) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "Update Question "
                    + "Set StatusId = ? Where id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 2);
            preStm.setInt(2, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateCorrectAnswerId(QuestionDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "UPDATE [dbo].[Question] "
                    + "   SET [CorrectAnswerId] = ? "
                    + " WHERE id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getCorrectAnswerId());
            preStm.setInt(2, dto.getId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuestionDTO> searchNameQuestion(String name) throws SQLException, NamingException {
        List<QuestionDTO> result = null;
        try {
            String sql = "Select Id, Content, CorrectAnswerId, CreateDate, SubjectId, StatusId From Question Where Content Like ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + name + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String content = rs.getString("Content");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                int statusId = rs.getInt("StatusId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, statusId, subjectId, correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuestionDTO> searchQuestionBySubjectId(String subjectId) throws SQLException, NamingException {
        List<QuestionDTO> result = null;
        try {
            String sql = "Select Id, Content, CorrectAnswerId, CreateDate, SubjectId, StatusId From Question Where SubjectId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, subjectId);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String content = rs.getString("Content");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int statusId = rs.getInt("StatusId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, statusId, Integer.parseInt(subjectId), correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuestionDTO> searchQuestionByStatusId(String statusId) throws SQLException, NamingException {
        List<QuestionDTO> result = null;
        try {
            String sql = "Select Id, Content, CorrectAnswerId, CreateDate, SubjectId, StatusId From Question Where StatusId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, statusId);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String content = rs.getString("Content");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, subjectId, Integer.parseInt(statusId), correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuestionDTO> getQuestionRandom(int subjectId, int numberOfQuestion) throws SQLException, NamingException {
        List<QuestionDTO> result = null;
        try {
            String sql = "Select top(?) Id, Content, CreateDate, StatusId, SubjectId, CorrectAnswerId "
                    + "From Question Where StatusId = ? "
                    + "AND SubjectId = ? "
                    + "ORDER BY NEWID() ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, numberOfQuestion);
            preStm.setInt(2, 1);
            preStm.setInt(3, subjectId);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String content = rs.getString("Content");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int id = rs.getInt("Id");
                int statusId = rs.getInt("StatusId");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                QuestionDTO dto = new QuestionDTO(id, content, createDate, statusId, subjectId, correctAnswerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public QuestionDTO getQuestionById(int id) throws SQLException, NamingException {
        QuestionDTO result = null;
        try {
            String sql = "SELECT [Id] "
                    + "      ,[Content] "
                    + "      ,[CorrectAnswerId] "
                    + "      ,[CreateDate] "
                    + "      ,[SubjectId] "
                    + "      ,[StatusId] "
                    + "From Question WHERE Id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                id = rs.getInt("Id");
                String content = rs.getString("Content");
                int correctAnswerId = rs.getInt("CorrectAnswerId");
                Timestamp createDate = rs.getTimestamp("CreateDate");
                int subjectId = rs.getInt("SubjectId");
                int statusId = rs.getInt("StatusId");
                result = new QuestionDTO(id, content, createDate, statusId, subjectId, correctAnswerId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
