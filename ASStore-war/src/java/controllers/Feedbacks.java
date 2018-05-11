/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Feedback;
import SB.FeedbackFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                List<Feedback> listFeedback = feedbackFacade.findAll();
                request.setAttribute("listFeedback", listFeedback);
                request.getRequestDispatcher("/user/feedback.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/feedback-create.jsp").forward(request, response);
                break;
            case "/details":
                Models.Feedback feedback = feedbackFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("feedback", feedback);
                request.getRequestDispatcher("/user/feedback-details.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
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

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/create":
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                String dateFB = request.getParameter("datefeedback");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 {
                    try {
                        Date dates = df.parse(dateFB);
                        Feedback feedback = new Feedback();
                        int id = Integer.parseInt(request.getParameter("id"));
                        Models.Users postedUser = usersFacade.find(id);
                        feedback.setUsersId(postedUser);
                        feedback.setId(0);
                        feedback.setTitle(title);
                        feedback.setContents(content);
                        feedback.setCreateAt(dates);

                        feedbackFacade.create(feedback);

                    } catch (ParseException ex) {
                        Logger.getLogger(Feedbacks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                response.sendRedirect(request.getContextPath() + "/feedbacks/create");
                response.getWriter().write("<script>alert('insert successful')</script>");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
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
