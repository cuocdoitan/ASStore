/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Feedback;
import Models.FeedbackComment;
import SB.FeedbackCommentFacadeLocal;
import SB.FeedbackFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRAN HO QUANG
 */
@WebServlet(name = "feedbacks", urlPatterns = {"/feedbacks/*"})

public class Feedbacks extends HttpServlet {

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private FeedbackFacadeLocal feedbackFacade;

    @EJB
    private FeedbackCommentFacadeLocal feedbackCommentFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Feedbacks</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Feedbacks at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                List<Feedback> listFeedback = feedbackFacade.getList();
                request.setAttribute("listFeedback", listFeedback);
                request.getRequestDispatcher("/user/feedback.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/feedback-create.jsp").forward(request, response);
                break;
            case "/details":
                request.setAttribute("listComment", feedbackCommentFacade.findAll());
                Models.Feedback feedback = feedbackFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("feedback", feedback);
                request.getRequestDispatcher("/user/feedback-details.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/create":
                createFeedback(request, response);
                break;
            case "/comment":
                commentFeedback(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;

        }
    }

    protected void createFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="action create new feedback">
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int id = Integer.parseInt(request.getParameter("id"));
        String errorMess = "";
        Date dates = new Date();
        Feedback feedback = new Feedback();
        boolean error = false;
        if (title.trim().equals("")) {
            errorMess = errorMess.equals("") ? "Title Feedback can't be blank" : errorMess;
            error = true;
        } else {
            request.setAttribute("title", title);
            feedback.setTitle(title);
        }

        if (content.trim().equals("")) {
            errorMess = errorMess.equals("") ? "Content Feedback can't be blank" : errorMess;
            error = true;
        } else {
            request.setAttribute("content", content);
            feedback.setContents(content);
        }
        if(error){
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/user/feedback-create.jsp").forward(request, response);
        }
        Models.Users postedUser = usersFacade.find(id);

        feedback.setCreateAt(dates);
        feedback.setEnabled(true);
        feedback.setUsersId(postedUser);
        feedback.setId(0);
        feedbackFacade.create(feedback);

        response.sendRedirect(request.getContextPath() + "/feedbacks/create");
        response.getWriter().write("<script>alert('insert successful')</script>");
        //</editor-fold
    }

    protected void commentFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="action comment feedback">
        int idfb = Integer.parseInt(request.getParameter("feedback_id"));
        Models.Feedback postedFb = feedbackFacade.find(idfb);
        String comment = request.getParameter("comment");
        Date datesfb = new Date();
        FeedbackComment feedbackcmt = new FeedbackComment();
        int iduser = Integer.parseInt(request.getParameter("user_id"));
        Models.Users postedUserfb = usersFacade.find(iduser);
        feedbackcmt.setUsersId(postedUserfb);
        feedbackcmt.setId(0);
        feedbackcmt.setFeedbackId(postedFb);
        feedbackcmt.setContents(comment);
        feedbackcmt.setCreateAt(datesfb);
        feedbackcmt.setEnabled(true);

        feedbackCommentFacade.create(feedbackcmt);
        request.setAttribute("listComment", feedbackCommentFacade.findAll());
        request.getRequestDispatcher("/user/components/feedback/feedbackCommentList.jsp").forward(request, response);
        //</editor-fold
    }

}
