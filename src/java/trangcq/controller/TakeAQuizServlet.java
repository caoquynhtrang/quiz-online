/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trangcq.question.QuestionDAO;
import trangcq.question.QuestionDTO;
import trangcq.quiz.QuizDTO;
import trangcq.quizQuestion.QuizQuestionDTO;
import trangcq.subject.SubjectDAO;
import trangcq.subject.SubjectDTO;
import trangcq.user.UserDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "TakeAQuizServlet", urlPatterns = {"/TakeAQuizServlet"})
public class TakeAQuizServlet extends HttpServlet {
    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TakeAQuizServlet.class);
    private final String takeAQuiz = "quiz.jsp";

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
        String txtSubjectId = request.getParameter("subjectId");
        try {
            int subjectId = Integer.parseInt(txtSubjectId);

            SubjectDAO subjectDAO = new SubjectDAO();
            SubjectDTO subject = subjectDAO.getSubjectById(subjectId);
            
            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> listQuestion = dao.getQuestionRandom(subjectId, subject.getQuestionCount());
            if (subject.getQuestionCount() == listQuestion.size()) {
                HttpSession session = request.getSession();
                QuizDTO quizDTO = new QuizDTO();
                UserDTO user = (UserDTO) session.getAttribute("USER");
                quizDTO.setEmail(user.getEmail());
                quizDTO.setSubjectId(subjectId);
                quizDTO.setCreateDate(new Timestamp(System.currentTimeMillis()));
                
                List<QuizQuestionDTO> listQuizQuestion = new ArrayList<>();
                for (QuestionDTO questionDTO : listQuestion) {
                    QuizQuestionDTO dto = new QuizQuestionDTO();
                    dto.setQuestionId(questionDTO.getId());
                    listQuizQuestion.add(dto);
                }
                quizDTO.setListQuizQuestion(listQuizQuestion);
                //Give user info about quiz to session
                session.setAttribute("USER_TAKE_QUIZ", quizDTO);
                
                int totalQuestions = listQuestion.size();
                int page = (int) Math.ceil(totalQuestions / 1);
                int[] pageArr = new int[page];
                
                session.setAttribute("LIST_QUESTION_QUIZ", listQuestion);
                request.setAttribute("QUESTION", listQuestion.get(0));
                session.setAttribute("TOTAL_PAGE", pageArr);
                session.setAttribute("COUNT_DOWN", subject.getDuration() * 60);
                
            } else{
                request.setAttribute("ERROR", "Not enough questions to take quiz!");
            }

        } catch (SQLException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            request.getRequestDispatcher(takeAQuiz).forward(request, response);
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
