/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.answer;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class AnswerDTO implements Serializable{
    private int id;
    private String content;
    private int questionid;

    public AnswerDTO() {
    }

    public AnswerDTO(int id, String content, int questionid) {
        this.id = id;
        this.content = content;
        this.questionid = questionid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
    
    
}
