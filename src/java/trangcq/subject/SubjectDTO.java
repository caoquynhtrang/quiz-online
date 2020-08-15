/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.subject;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class SubjectDTO implements Serializable{
    private int id, questionCount, duration;
    private String code, name;

    public SubjectDTO() {
    }

    public SubjectDTO(int questionCount, int duration) {
        this.questionCount = questionCount;
        this.duration = duration;
    }

    public SubjectDTO(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    
    

    public SubjectDTO(int id, int questionCount, int duration, String code, String name) {
        this.id = id;
        this.questionCount = questionCount;
        this.duration = duration;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
