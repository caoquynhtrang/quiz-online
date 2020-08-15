/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trangcq.question.QuestionDAO;
import trangcq.question.QuestionDTO;
import trangcq.status.StatusDAO;
import trangcq.status.StatusDTO;
import trangcq.subject.SubjectDAO;
import trangcq.subject.SubjectDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "SearchSubjectServlet", urlPatterns = {"/SearchSubjectServlet"})
public class SearchSubjectServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SearchSubjectServlet.class);
    private final String searchSubject = "adminPage.jsp";

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
        String subjectId = request.getParameter("subjectId");
        try {
            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> list = dao.searchQuestionBySubjectId(subjectId);
            request.setAttribute("LIST", list);
            SubjectDAO subjectDAO = new SubjectDAO();
            List<SubjectDTO> dto = subjectDAO.getAllSubject();
            request.setAttribute("SUBJECT", dto);
            
            StatusDAO statusDAO = new StatusDAO();
            List<StatusDTO> statusDTO = statusDAO.getAllStatus();
            request.setAttribute("STATUS", statusDTO);
        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            request.getRequestDispatcher(searchSubject).forward(request, response);
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
