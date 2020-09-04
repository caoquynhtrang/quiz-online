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
import javax.servlet.http.HttpSession;
import trangcq.user.UserDAO;
import trangcq.user.UserDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoginServlet.class);
    private final String invalidPage = "invalid.html";
    private final String adminPage = "DispatcherController?btAction=Show Question";
    private final String userPage = "DispatcherController?btAction=ShowUserPage";

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
        String url = invalidPage;
        try {
            String encryPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            UserDAO dao = new UserDAO();
            UserDTO result = dao.checkLogin(email, encryPassword);
            if (result != null) {
                if (result.getRole().getName().equalsIgnoreCase("Student")) {
                    url = userPage;
                } else if (result.getRole().getName().equalsIgnoreCase("Teacher")) {
                    url = adminPage;
                }
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            ex.printStackTrace();
            LOGGER.fatal(ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
