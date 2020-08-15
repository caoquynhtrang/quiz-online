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
import trangcq.question.CreateQuestionErr;
import trangcq.question.QuestionDAO;
import trangcq.question.QuestionDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CreateServlet.class);
    private final String showSubjectPage = "ShowSubjectServlet";
    private final String showQuestion = "DispatcherController?btAction=Show Question";

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
        CreateQuestionErr errors = new CreateQuestionErr();
        String question = request.getParameter("txtQuestion");
        String answerA = request.getParameter("txtAnswerA");
        String answerB = request.getParameter("txtAnswerB");
        String answerC = request.getParameter("txtAnswerC");
        String answerD = request.getParameter("txtAnswerD");
        String correctAnswer = request.getParameter("txtCorrectAnswer");
        String subjectId = request.getParameter("subjectId");
        String url = showSubjectPage;
        try {
            boolean error = false;
            if (question.trim().length() < 10 || question.trim().length() > 100) {
                error = true;
                errors.setLengthQuestionErr("Question must be from 10 to 100 characters");
            }
            if (answerA.trim().length() < 10 || answerA.trim().length() > 50) {
                error = true;
                errors.setLengthAnswerAErr("Answer A must be from 10 to 50 characters");
            }
            if (answerB.trim().length() < 10 || answerB.trim().length() > 50) {
                error = true;
                errors.setLengthAnswerBErr("Answer B must be from 10 to 50 characters");
            }
            if (answerC.trim().length() < 10 || answerC.trim().length() > 50) {
                error = true;
                errors.setLengthAnswerCErr("Answer C must be from 10 to 50 characters");
            }
            if (answerD.trim().length() < 10 || answerD.trim().length() > 50) {
                error = true;
                errors.setLengthAnswerDErr("Answer D must be from 10 to 50 characters");
            }
            if (correctAnswer.trim().isEmpty()) {
                error = true;
                errors.setCorrectAnswerErr("Correct Answer must be A or B or C or D");
            }

            if (error) {
                request.setAttribute("CREATEQUESTION", errors);
            } else {

                QuestionDAO questionDAO = new QuestionDAO();
                QuestionDTO dto = new QuestionDTO();
                dto.setContent(question);
                dto.setSubjectId(Integer.parseInt(subjectId));
                int questionId = questionDAO.insertQuestion(dto);
                dto.setId(questionId);

                AnswerDAO ansDao = new AnswerDAO();
                int idAnswerA = ansDao.insertAnswer(answerA, questionId);
                int idAnswerB = ansDao.insertAnswer(answerB, questionId);
                int idAnswerC = ansDao.insertAnswer(answerC, questionId);
                int idAnswerD = ansDao.insertAnswer(answerD, questionId);

                if (idAnswerA == -1 || idAnswerB == -1 || idAnswerC == -1 || idAnswerD == -1) {
                    url = showSubjectPage;
                }
                
                boolean result = true;
                if (correctAnswer.trim().equalsIgnoreCase("A")) {
                    dto.setCorrectAnswerId(idAnswerA);
                    result = questionDAO.updateCorrectAnswerId(dto);
                } else if (correctAnswer.trim().equalsIgnoreCase("B")) {
                    dto.setCorrectAnswerId(idAnswerB);
                    result = questionDAO.updateCorrectAnswerId(dto);
                } else if (correctAnswer.trim().equalsIgnoreCase("C")) {
                    dto.setCorrectAnswerId(idAnswerC);
                    result = questionDAO.updateCorrectAnswerId(dto);
                } else if (correctAnswer.trim().equalsIgnoreCase("D")) {
                    dto.setCorrectAnswerId(idAnswerD);
                    result = questionDAO.updateCorrectAnswerId(dto);
                }

                if (result) {
                    url = showQuestion;
                    
                }
            }

        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            if(url.equals(showQuestion)){
                response.sendRedirect(showQuestion);
            }else {
                request.getRequestDispatcher(url).forward(request, response);
            }
            
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
