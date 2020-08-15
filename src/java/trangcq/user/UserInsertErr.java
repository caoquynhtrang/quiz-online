/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.user;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class UserInsertErr implements Serializable{
    private String emailLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatch;
    private String nameIsExist;
    private String fullnameLengthErr;

    public UserInsertErr() {
    }

    public UserInsertErr(String emailLengthErr, String passwordLengthErr, String confirmNotMatch, String nameIsExist, String fullnameLengthErr) {
        this.emailLengthErr = emailLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmNotMatch = confirmNotMatch;
        this.nameIsExist = nameIsExist;
        this.fullnameLengthErr = fullnameLengthErr;
    }

    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }
    
    
    
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getNameIsExist() {
        return nameIsExist;
    }

    public void setNameIsExist(String nameIsExist) {
        this.nameIsExist = nameIsExist;
    }
    
    
}
