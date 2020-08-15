/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.quizQuestion;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import trangcq.question.QuestionDAO;
import trangcq.question.QuestionDTO;

/**
 *
 * @author USER
 */
public class QuizQuestionDTO implements Serializable{
    private int id, quizId, questionId, answerId;
    private QuestionDTO questionDTO;

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    
    public QuizQuestionDTO() {
    }

    public QuizQuestionDTO(int id, int quizId, int questionId, int answerId) throws SQLException, NamingException {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
        this.answerId = answerId;
        QuestionDAO questionDAO = new QuestionDAO();
        this.questionDTO = questionDAO.getQuestionById(questionId);
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    
    
    
    
}
