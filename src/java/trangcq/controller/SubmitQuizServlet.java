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
import trangcq.quiz.QuizDAO;
import trangcq.quiz.QuizDTO;
import trangcq.quizQuestion.QuizQuestionDAO;
import trangcq.quizQuestion.QuizQuestionDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SubmitQuizServlet.class);
    private final String submitQuiz = "submitQuiz.jsp";

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
        
        try {
            HttpSession session = request.getSession();
            QuizDTO quizDTO = (QuizDTO) session.getAttribute("USER_TAKE_QUIZ");
            QuizDAO quizDAO = new QuizDAO();
            
            int resultQuizId = quizDAO.insertQuiz(quizDTO);
            QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
            for (QuizQuestionDTO quizQuestionDTO : quizDTO.getListQuizQuestion()) {
                quizQuestionDTO.setQuizId(resultQuizId);
                boolean resultQuizQues = quizQuestionDAO.insertQuizQuestion(quizQuestionDTO);
            }
            QuizDTO quiz = quizDAO.getQuizById(resultQuizId);
            int correct = quiz.countCorrect();
            double mark = quiz.giveMark();
            int totalQues = quiz.getSubjectDTO().getQuestionCount();
            request.setAttribute("CORRECT", correct);
            request.setAttribute("MARK", mark);
            request.setAttribute("TOTAL", totalQues);
            
        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally{
            request.getRequestDispatcher(submitQuiz).forward(request, response);
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
