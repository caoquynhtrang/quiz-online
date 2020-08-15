/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.question;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class CreateQuestionErr implements Serializable{
    private String lengthQuestionErr;
    private String lengthAnswerAErr;
    private String lengthAnswerBErr;
    private String lengthAnswerCErr;
    private String lengthAnswerDErr;
    private String correctAnswerErr;

    public CreateQuestionErr() {
    }

    public CreateQuestionErr(String lengthQuestionErr, String lengthAnswerAErr, String lengthAnswerBErr, String lengthAnswerCErr, String lengthAnswerDErr, String correctAnswerErr) {
        this.lengthQuestionErr = lengthQuestionErr;
        this.lengthAnswerAErr = lengthAnswerAErr;
        this.lengthAnswerBErr = lengthAnswerBErr;
        this.lengthAnswerCErr = lengthAnswerCErr;
        this.lengthAnswerDErr = lengthAnswerDErr;
        this.correctAnswerErr = correctAnswerErr;
    }

    public String getLengthQuestionErr() {
        return lengthQuestionErr;
    }

    public void setLengthQuestionErr(String lengthQuestionErr) {
        this.lengthQuestionErr = lengthQuestionErr;
    }

    public String getLengthAnswerAErr() {
        return lengthAnswerAErr;
    }

    public void setLengthAnswerAErr(String lengthAnswerAErr) {
        this.lengthAnswerAErr = lengthAnswerAErr;
    }

    public String getLengthAnswerBErr() {
        return lengthAnswerBErr;
    }

    public void setLengthAnswerBErr(String lengthAnswerBErr) {
        this.lengthAnswerBErr = lengthAnswerBErr;
    }

    public String getLengthAnswerCErr() {
        return lengthAnswerCErr;
    }

    public void setLengthAnswerCErr(String lengthAnswerCErr) {
        this.lengthAnswerCErr = lengthAnswerCErr;
    }

    public String getLengthAnswerDErr() {
        return lengthAnswerDErr;
    }

    public void setLengthAnswerDErr(String lengthAnswerDErr) {
        this.lengthAnswerDErr = lengthAnswerDErr;
    }

    public String getCorrectAnswerErr() {
        return correctAnswerErr;
    }

    public void setCorrectAnswerErr(String correctAnswerErr) {
        this.correctAnswerErr = correctAnswerErr;
    }

   

    
    
    
}
