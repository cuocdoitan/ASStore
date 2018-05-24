/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import BusinessLogic.PaginationHandler;
import Models.Feedback;
import Models.FeedbackComment;
import Models.Roles;
import SB.FeedbackCommentFacadeLocal;
import SB.FeedbackFacadeLocal;
import SB.RolesFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN HO QUANG
 */
@WebServlet(name = "feedbacks", urlPatterns = {"/feedbacks/*"})

public class Feedbacks extends HttpServlet {

    @EJB
    private RolesFacadeLocal rolesFacade;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute("phone");
        if (phone == null) {
            requestForClient(request, response);
        } else {
            requestForUser(request, response);
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
        //<editor-fold defaultstate="collapsed" desc="action create new feedback">
        HttpSession session = request.getSession();
        int iduser = (int) session.getAttribute("userid");

        String title = request.getParameter("title");
        String content = request.getParameter("content");
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
        }
        if (content.trim().length() < 20) {
            errorMess = errorMess.equals("") ? "Content Feedback 20 character" : errorMess;
            error = true;
        } else {
            request.setAttribute("content", content);
            feedback.setContents(content);
        }

        if (error) {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/user/feedback-create.jsp").forward(request, response);
        }

        Models.Users postedUserCreate = usersFacade.find(iduser);

        feedback.setCreateAt(dates);
        feedback.setEnabled(true);
        feedback.setUsersId(postedUserCreate);
        feedback.setId(0);
        feedbackFacade.create(feedback);

        response.sendRedirect(request.getContextPath() + "/feedbacks/list");
//        response.getWriter().write("<script>alert('insert successful')</script>");
        //</editor-fold
    }

    protected void commentFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="action comment feedback">

        HttpSession session = request.getSession();
        int iduser = (int) session.getAttribute("userid");
        Models.Users postedUserfb = usersFacade.find(iduser);

        int idfb = Integer.parseInt(request.getParameter("feedback_id"));
        Models.Feedback postedFb = feedbackFacade.find(idfb);

        String comment = request.getParameter("comment");
        Date datesfb = new Date();
        FeedbackComment feedbackcmt = new FeedbackComment();

        feedbackcmt.setUsersId(postedUserfb);
        feedbackcmt.setId(0);
        feedbackcmt.setFeedbackId(postedFb);
        feedbackcmt.setContents(comment);
        feedbackcmt.setCreateAt(datesfb);
        feedbackcmt.setEnabled(true);

        feedbackCommentFacade.create(feedbackcmt);
        request.setAttribute("listComment", feedbackCommentFacade.getListComment(idfb));
        request.getRequestDispatcher("/user/components/feedback/feedbackCommentList.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="action delete feedback">

        int idfb = Integer.parseInt(request.getParameter("idfeedbackdelete"));
        int idfbcmt = Integer.parseInt(request.getParameter("idcmt"));
        Models.FeedbackComment fbcmt = feedbackCommentFacade.find(idfbcmt);
        feedbackCommentFacade.remove(fbcmt);
        request.setAttribute("listComment", feedbackCommentFacade.getListComment(idfb));
        request.getRequestDispatcher("/user/components/feedback/feedbackCommentList.jsp").forward(request, response);

        //</editor-fold>
    }

    public void requestForClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                List<Feedback> listFeedback = feedbackFacade.getList();
                String page = request.getParameter("page");
                PaginationHandler pagination = new PaginationHandler();
                int selectedPage = pagination.getSelectedPage(page);
                int maxPage = pagination.countNumberOfPages(listFeedback.size(), 5);
                if (selectedPage < 0 || selectedPage > maxPage) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                int[] range = pagination.getObjectPositionInSelectedPage(listFeedback.size(), 5, selectedPage);
                List<Feedback> listFeedbacksInPage = listFeedback.subList(range[0], range[1]);
                request.setAttribute("listFeedback", listFeedbacksInPage);
//                request.setAttribute("listFeedback", listFeedback);
                request.setAttribute("numberOfPage", maxPage);
                request.setAttribute("selectedPage", selectedPage);
                String queryString = request.getQueryString();
                if (page != null) {
                    int indexOfPage = request.getQueryString().indexOf("&page=");
                    String queryStringWithoutPage = request.getQueryString().substring(0, indexOfPage);
                    queryString = queryStringWithoutPage;
                }
                request.setAttribute("queryString", queryString);
                request.getRequestDispatcher("/user/feedback.jsp").forward(request, response);
                break;
            case "/create":
//                response.sendRedirect(request.getContextPath() + "/User/login");
                response.sendRedirect(request.getContextPath() + "/User/login");
                break;
            case "/details":
//                response.sendRedirect(request.getContextPath() + "/User/login");
                response.sendRedirect(request.getContextPath() + "/User/login");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    public void requestForUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        String phone = (String) session.getAttribute("phone");
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                List<Feedback> listFeedback = feedbackFacade.getList();
                String page = request.getParameter("page");
                PaginationHandler pagination = new PaginationHandler();
                int selectedPage = pagination.getSelectedPage(page);
                int maxPage = pagination.countNumberOfPages(listFeedback.size(), 5);
                if (selectedPage < 0 || selectedPage > maxPage) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                int[] range = pagination.getObjectPositionInSelectedPage(listFeedback.size(), 5, selectedPage);
                List<Feedback> listFeedbacksInPage = listFeedback.subList(range[0], range[1]);
                request.setAttribute("listFeedback", listFeedbacksInPage);
//                request.setAttribute("listFeedback", listFeedback);
                request.setAttribute("numberOfPage", maxPage);
                request.setAttribute("selectedPage", selectedPage);
                String queryString = request.getQueryString();
                if (page != null) {
                    int indexOfPage = request.getQueryString().indexOf("&page=");
                    String queryStringWithoutPage = request.getQueryString().substring(0, indexOfPage);
                    queryString = queryStringWithoutPage;
                }
                request.setAttribute("queryString", queryString); 
                request.getRequestDispatcher("/user/feedback.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/feedback-create.jsp").forward(request, response);
                break;
            case "/details":
                Models.Feedback feedback = feedbackFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("feedback", feedback);
                request.setAttribute("listComment", feedbackCommentFacade.getListComment(feedback.getId()));
                HttpSession session = request.getSession();
                int iduser = (int) session.getAttribute("userid");
                Models.Roles roleId = rolesFacade.find(1);
                request.setAttribute("roleId", roleId);
                request.setAttribute("userid", iduser);
                request.setAttribute("feedbackId", feedback.getId());
                request.getRequestDispatcher("/user/feedback-details.jsp").forward(request, response);
                break;
            case "/deletecomment":
                deleteFeedback(request, response);
                break;
            case "/deletefeeback":
                int fbacksId = Integer.parseInt(request.getParameter("fbsid"));
                Models.Feedback fbDelete = feedbackFacade.find(fbacksId);
                fbDelete.setEnabled(false);
                feedbackFacade.edit(fbDelete);
                request.getRequestDispatcher("/feedbacks/list").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}
