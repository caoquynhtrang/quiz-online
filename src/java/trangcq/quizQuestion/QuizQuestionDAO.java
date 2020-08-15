/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.quizQuestion;

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
public class QuizQuestionDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public QuizQuestionDAO() {
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

    public boolean insertQuizQuestion(QuizQuestionDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "INSERT INTO [dbo].[QuizQuestion] "
                    + "           ([QuizId] "
                    + "           ,[QuestionId] "
                    + "           ,[AnswerId]) "
                    + "     VALUES(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getQuizId());
            preStm.setInt(2, dto.getQuestionId());
            preStm.setInt(3, dto.getAnswerId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<QuizQuestionDTO> getQuizQuestionByQuizId(int quizId) throws SQLException, NamingException {
        List<QuizQuestionDTO> result = null;
        try {
            String sql = " Select [Id], [QuizId], [QuestionId], [AnswerId] "
                    + " From  [dbo].[QuizQuestion]  Where QuizId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quizId);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                quizId = rs.getInt("QuizId");
                int questionId = rs.getInt("QuestionId");
                int answerId = rs.getInt("AnswerId");
                QuizQuestionDTO dto = new QuizQuestionDTO(id, quizId, questionId, answerId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
