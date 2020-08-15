/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.user;

import java.io.Serializable;
import trangcq.role.RoleDTO;
import trangcq.status.StatusDTO;

/**
 *
 * @author USER
 */
public class UserDTO implements Serializable{
    private String email, name, password, fullname;
    private StatusDTO status;
    private RoleDTO role;

    public UserDTO() {
    }

    public UserDTO(String email, String name, String password, String fullname) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.fullname = fullname;
    }

    public UserDTO(String email, String name, String fullname, StatusDTO status, RoleDTO role) {
        this.email = email;
        this.name = name;
        this.fullname = fullname;
        this.status = status;
        this.role = role;
    }

    
   
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

   
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    
}
