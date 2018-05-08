/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Users;
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

/**
 *
 * @author leminhtung
 */
@WebServlet(name = "User", urlPatterns = {"/User/*"})
public class User extends HttpServlet {

    @EJB
    private RolesFacadeLocal rolesFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet User</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet User at " + request.getContextPath() + "</h1>");
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
                java.util.List<Models.Users> listaccount = usersFacade.findAll();
                request.setAttribute("userlist", listaccount);
                request.getRequestDispatcher("/admin/user-accountlist.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
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
                String phone = request.getParameter("phone");
                String firstname = request.getParameter("first_name");
                String lastname = request.getParameter("last_name");
                String password = request.getParameter("password");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                try {
                    Models.Users user = new Users();
                    user.setId(0);
                    user.setPhoneNumber(phone);
                    user.setFirstName(firstname);
                    user.setLastName(lastname);
                    user.setPassword(password);
                    user.setAddress(address);
                    user.setEmail(email);
                    user.setCreateAt(new Date());
                    user.setEnabled(true);
                    user.setNumberOfNotification(0);
                    user.setRolesId(rolesFacade.find(3));
                    usersFacade.create(user);
                } catch (Exception e) {
                    System.out.println("============================== ");
                    System.out.println("Exception : ");
                    System.out.println(e.toString());
                }
                response.sendRedirect(request.getContextPath() + "/User/create");
                break;
            case "/update":
                request.getRequestDispatcher("/admin/anime-list-update.jsp").forward(request, response);
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
