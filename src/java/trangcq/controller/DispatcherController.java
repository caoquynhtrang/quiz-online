/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class DispatcherController extends HttpServlet {
    private final String loginPage = "login.jsp";
    private final String loginServlet = "LoginServlet";
    private final String createAccountServlet = "CreateAccountServlet";
    private final String createServlet = "CreateServlet";
    private final String searchQuestion ="SearchQuestionServlet";
    private final String createQuestion ="CreateServlet";
    private final String showQuestion = "ShowQuestionServlet";
    private final String deleteQuestion = "DeleteQuestionServlet";
    private final String updateQuestion = "UpdateQuestionServlet";
    private final String showSubject = "ShowSubjectServlet";
    private final String searchSubject = "SearchSubjectServlet";
    private final String showStatus = "ShowStatusServlet";
    private final String searchStatus = "SearchStatusServlet";
    private final String showUserPage = "ShowUserPageServlet";
    private final String takeAQuiz = "TakeAQuizServlet";
    private final String quiz = "quiz.jsp";
    private final String changePageQuiz = "ChangePageQuizServlet";
    private final String submitQuiz = "SubmitQuizServlet";
    private final String showHistory = "ShowHistoryServlet";
    private final String viewHistory = "viewHistory.jsp";
    private final String LOGOUT = "LogoutServlet";
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
        String button = request.getParameter("btAction");
        String url = loginPage;
        try {
            if (button == null) {
            } else if (button.equals("Login")) {
                url = loginServlet;
            }else if(button.equals("Create New Account")){
                url = createAccountServlet;
            } else if(button.equals("Add Question")){
                url = createServlet;
            } else if(button.equals("Search")){
                url = searchQuestion;
            } else if (button.equals("Create Question")){
                url = createQuestion;
            } else if(button.equals("Show Question") || button.equals("Change Page")){
                url= showQuestion;
            } else if(button.equals("Delete")){
                url = deleteQuestion;
            } else if(button.equals("Update")){
                url= updateQuestion;
            } else if(button.equals("Show Subject")){
                url = showSubject;
            } else if (button.equals("Search Subject")){
                url = searchSubject;
            } else if (button.equals("Show Status")){
                url = showStatus;
            } else if (button.equals("Search Status")) {
                url = searchStatus;
            } else if (button.equals("ATTEMPT QUIZ NOW")){
                url = takeAQuiz;
            } else if(button.equals("ShowUserPage")){
                url = showUserPage;
            } else if(button.equals("Change Page Quiz")){
                url = changePageQuiz;
            } else if (button.equals("Submit Quiz")){
                url = submitQuiz;
            } else if (button.equals("View History")){
                url = showHistory;
            }else if (button.equals("Logout")){
                url=LOGOUT;
            }
                    

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
