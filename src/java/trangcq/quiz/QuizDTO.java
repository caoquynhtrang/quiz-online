/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.quiz;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.naming.NamingException;
import trangcq.quizQuestion.QuizQuestionDAO;
import trangcq.quizQuestion.QuizQuestionDTO;
import trangcq.subject.SubjectDAO;
import trangcq.subject.SubjectDTO;

/**
 *
 * @author USER
 */
public class QuizDTO implements Serializable {

    private int id;
    private Timestamp createDate;
    
    private int subjectId;
    private String email;
    private List<QuizQuestionDTO> listQuizQuestion;
    private SubjectDTO subjectDTO;

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }
    
    

    public boolean checkChosenAnswer(int answerId, int questionId) {
        for (QuizQuestionDTO quizQuestionDTO : listQuizQuestion) {
            if (quizQuestionDTO.getQuestionId() == questionId) {
                if (quizQuestionDTO.getAnswerId() == answerId) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<QuizQuestionDTO> getListQuizQuestion() {
        return listQuizQuestion;
    }

    public void setListQuizQuestion(List<QuizQuestionDTO> listQuizQuestion) {
        this.listQuizQuestion = listQuizQuestion;
    }

    public QuizDTO() {
    }

    public int countCorrect() {
        int count = 0;
        for (QuizQuestionDTO quizQuestionDTO : listQuizQuestion) {
            if (quizQuestionDTO.getAnswerId() == quizQuestionDTO.getQuestionDTO().getCorrectAnswerId()) {
                count++;
            }
        }
        return count;
    }

    public double giveMark() {
        int correct = countCorrect();
        return Math.round(((double) correct / subjectDTO.getQuestionCount()) * 100.0) / 100.0;
    }

    public QuizDTO(int id, Timestamp createDate, int subjectId, String email) throws SQLException, NamingException {
        this.id = id;
        this.createDate = createDate;
        
        this.subjectId = subjectId;
        this.email = email;
        QuizQuestionDAO quizQuestionDao = new QuizQuestionDAO();
        this.listQuizQuestion = quizQuestionDao.getQuizQuestionByQuizId(id);
        SubjectDAO subjectDAO = new SubjectDAO();
        this.subjectDTO = subjectDAO.getSubjectById(subjectId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
