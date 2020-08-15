/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trangcq.user.UserDAO;
import trangcq.user.UserInsertErr;

/**
 *
 * @author USER
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CreateAccountServlet.class);
    private final String showInserErr = "createAccount.jsp";
    private final String loginPage = "login.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        String fullname = request.getParameter("txtFullname");
        UserInsertErr errors = new UserInsertErr();
        String url = showInserErr;
        boolean foundErr = false;
        try {
            if(email.trim().length() < 11 || email.trim().length() > 30){
                foundErr = true;
                errors.setEmailLengthErr("Email string is required from 11 to 30 characters");
            }
            if(password.trim().length() < 6 || password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthErr("Password string is required from 6 to 30 characters");
            }
            if(!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            if(name.trim().length() < 2 || name.trim().length() > 50){
                foundErr = true;
                errors.setNameIsExist("Name string is required from 2 to 20 characters");
            }
            if(fullname.trim().length() < 5 || fullname.trim().length() > 50){
                foundErr = true;
                errors.setFullnameLengthErr("Fullname string is required from 5 to 50 characters");
            }
            if(foundErr){
                request.setAttribute("CREATEERROR", errors);
            } else{
                String encryPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
                UserDAO dao = new UserDAO();
                boolean result = dao.insertAccount(email, encryPassword, name, fullname);
                if(result){
                    url = loginPage;
                }
            }
            
        
        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
            errors.setEmailLengthErr(email + "Existed!!!");
            request.setAttribute("INSERTERR", errors);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
