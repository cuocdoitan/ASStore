/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import Models.Category;
import SB.CategoryFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN HO QUANG
 */
@WebServlet(name = "category_admin", urlPatterns = {"/admin/category/*"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class Category_admin extends HttpServlet {

    @EJB
    private CategoryFacadeLocal categoryFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet category_admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet category_admin at " + request.getContextPath() + "</h1>");
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
//        HttpSession sesion = request.getSession();
//        if(sesion == null){
//            requestsForClients(request, response);
//        }else{
//            requestsForUsers(request, response);
//        }
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                List<Category> listCategory = categoryFacade.findAll();
                request.setAttribute("listCategory", listCategory);
                request.getRequestDispatcher("/admin/category-list.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/admin/category-create.jsp").forward(request, response);
                break;
            case "/edit":
                Models.Category category = categoryFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("category", category);
                request.getRequestDispatcher("/admin/category-edit.jsp").forward(request, response);
                break;
            case "/delete":
                Models.Category category1 = categoryFacade.find(Integer.parseInt(request.getParameter("id")));
                categoryFacade.remove(category1);
                request.getRequestDispatcher("/admin/category/list").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("do post");
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/create":
                request.getRequestDispatcher("/UploadCategoryImages").forward(request, response);
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

    public void requestsForUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                request.getRequestDispatcher("/admin/category-list.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/admin/category-create.jsp").forward(request, response);
                break;
            case "/edit":
                request.getRequestDispatcher("/admin/category-edit.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    public void requestsForClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/list":
                request.getRequestDispatcher("/admin/category-list.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    
}
