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
import trangcq.answer.AnswerDAO;
import trangcq.answer.AnswerDTO;
import trangcq.question.QuestionDAO;
import trangcq.question.QuestionDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UpdateQuestionServlet.class);
    private final String error = "errorU.html";
    private final String adminPage = "DispatcherController?btAction=Show Question";
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
        String content = request.getParameter("txtContent");
        int questionId = Integer.parseInt(request.getParameter("txtQuestionId"));
        String correctAns = request.getParameter("correctAnsId");
        String page = request.getParameter("txtPage");
        
        String url = error;
        try {
            QuestionDAO dao = new QuestionDAO();
            QuestionDTO dto = new QuestionDTO();
            dto.setId(questionId);
            dto.setContent(content);
            dto.setCorrectAnswerId(Integer.parseInt(correctAns));
            AnswerDAO answerDAO = new AnswerDAO();
            
            String[] anwserIdArray = request.getParameterValues("txtAnswer");
            for (String ansId : anwserIdArray) {
                String ansContent = request.getParameter(ansId);
                AnswerDTO ansDTO = new AnswerDTO();
                ansDTO.setId(Integer.parseInt(ansId));
                ansDTO.setContent(ansContent);
                answerDAO.updateAnswer(ansDTO);
            }
            boolean updateQ = dao.updateQuestion(dto);
            if(updateQ){
                if(page == null){
                    page = "1";
                }
                url = adminPage 
                        + "&page=" + page;
            }
            
        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally{
            response.sendRedirect(url);
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
