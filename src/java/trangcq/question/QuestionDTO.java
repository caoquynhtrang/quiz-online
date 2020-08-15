/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.question;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import trangcq.answer.AnswerDAO;
import trangcq.answer.AnswerDTO;

/**
 *
 * @author USER
 */
public class QuestionDTO implements Serializable {

    private int id, correctAnswerId, subjectId, statusId;
    private String content;
    private Timestamp createDate;
    private List<AnswerDTO> answerList;

    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDTO> answerList) {
        this.answerList = answerList;
    }

    public QuestionDTO() {

    }

    public QuestionDTO(int correctAnswerId, String content) {
        this.correctAnswerId = correctAnswerId;
        this.content = content;
    }

    

    
    public QuestionDTO(String content, List<AnswerDTO> answerList) {
        this.content = content;
        this.answerList = answerList;
    }

    
    public QuestionDTO(int id, String content, Timestamp createDate, int statusId, int subjectId, int correctAnswerId) {
        this.content = content;
        this.createDate = createDate;
        this.id = id;
        this.statusId = statusId;
        this.correctAnswerId = correctAnswerId;
        AnswerDAO dao = new AnswerDAO();
        try {
            answerList = (List<AnswerDTO>) dao.getAnswerOfQuestion(id);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(QuestionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getAnsContent(){
        for (AnswerDTO answerDTO : answerList) {
            if(answerDTO.getId() == this.correctAnswerId){
                return answerDTO.getContent();
            }
        }
        return null;
    }
    
    
    

    public QuestionDTO(int id, int correctAnswerId, int subjectId, int statusId, String content, Timestamp createDate) {
        this.id = id;
        this.correctAnswerId = correctAnswerId;
        this.subjectId = subjectId;
        this.statusId = statusId;
        this.content = content;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

}
