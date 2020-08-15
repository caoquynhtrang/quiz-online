/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trangcq.question.QuestionDTO;
import trangcq.quiz.QuizDTO;
import trangcq.quizQuestion.QuizQuestionDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "ChangePageQuizServlet", urlPatterns = {"/ChangePageQuizServlet"})
public class ChangePageQuizServlet extends HttpServlet {
    
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
        String page = request.getParameter("page");
        String txtQuestionId = request.getParameter("txtQuestionId");
        String txtAnswerId = request.getParameter("txtAnswerId");
        String txtCountDown = request.getParameter("txtCountDown");
        try {
            
            HttpSession session = request.getSession();
            //Lay cau hoi ve hien thi
            List<QuestionDTO> questionQuizList = (List<QuestionDTO>) session.getAttribute("LIST_QUESTION_QUIZ");
            int pageIndex = Integer.parseInt(page);
            request.setAttribute("QUESTION", questionQuizList.get(pageIndex - 1));
            
            //Lay cau hoi nguoi dung dang lam
            int questionId = Integer.parseInt(txtQuestionId);
            QuizDTO quiz = (QuizDTO) session.getAttribute("USER_TAKE_QUIZ");
            for (QuizQuestionDTO quizQuestionDTO : quiz.getListQuizQuestion()) {
                if(quizQuestionDTO.getQuestionId() == questionId){
                    //Neu co dap an duoc chon
                    if(txtAnswerId != null){
                        int answerId = Integer.parseInt(txtAnswerId);
                        //Thi set gia tri cho bien quiz question tuong ung
                        quizQuestionDTO.setAnswerId(answerId);
                    }
                }
            }
            session.setAttribute("USER_TAKE_QUIZ", quiz);
            session.setAttribute("COUNT_DOWN", txtCountDown);
            
        } finally{
            request.getRequestDispatcher("quiz.jsp").forward(request, response);
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
