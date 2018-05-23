/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Roles;
import Models.Users;
import SB.RolesFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leminhtung
 */
@WebServlet(name = "User_admin", urlPatterns = {"/admin/user/*"})
public class User_admin extends HttpServlet {

    @EJB
    private RolesFacadeLocal rolesFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

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
        HttpSession sess = request.getSession();
        String adminRequest = request.getPathInfo();
        switch (adminRequest) {
            case "/list":
                Roles role = (Roles) sess.getAttribute("role");
                if (!role.getName().equals("admin")) {
                    request.getRequestDispatcher("/admin/no-permission.jsp").forward(request, response);
                    return;
                }
                listAdminPage(request, response);
                break;
            case "/searchByPhone":
                searchByPhone(request, response);
                break;
            case "/userinfo":
                userInfoPage(request, response);
                break;
            case "/loginadmin":
                loginAdminPage(request, response);
                break;
            case "/delete":
                Models.Users user1 = usersFacade.find(Integer.parseInt(request.getParameter("id")));
                if (user1.getRolesId().getName().equals("admin")) {
                    request.setAttribute("error", "Admin not delete");
                    request.getRequestDispatcher("/User/list").forward(request, response);
                } else {
                    user1.setEnabled(false);
                    usersFacade.edit(user1);
                    request.getRequestDispatcher("/User/list").forward(request, response);
                }
                break;
            case "/logoutadmin":
                logoutAdmin(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void listAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to create product page">
        HttpSession sess = request.getSession();
        if (sess.getAttribute("userid") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/user/loginadmin");
            return;
        }
        java.util.List<Models.Users> listaccount = usersFacade.getList();
        listaccount.remove(usersFacade.find(1));
        request.setAttribute("userlist", listaccount);
        request.getRequestDispatcher("/admin/user-accountlist.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void searchByPhone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="search by phone">
        String phone = request.getParameter("phone");
        Users u = usersFacade.getUsersByPhone(phone);
        List<Users> userlist = new ArrayList<>();
        if (u != null) {
            userlist.add(u);
        }
        request.setAttribute("userlist", userlist);
        request.getRequestDispatcher("/admin/Permission.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void userInfoPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to create product page">
        HttpSession sess = request.getSession();
        Models.Users userinfo = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
        request.setAttribute("userid", userinfo);
        request.getRequestDispatcher("/admin/user-detail.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void loginAdminPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to create product page">
        request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void logoutAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to create product page">
        HttpSession sess = request.getSession();
        sess.invalidate();
        response.sendRedirect(request.getContextPath() + "/admin/user/loginadmin");
        //</editor-fold>
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
        HttpSession sess = request.getSession();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/loginadmin":
                loginadmin(request, response);
                break;
        }
    }

    protected void loginadmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action terminate rating product">
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        Users loginedUser = usersFacade.getUsersByPhone(phone);
        Roles loginadmin = rolesFacade.find(1);
        if (loginedUser == null) {
            //account khong ton tai
            request.setAttribute("phone", phone);
            request.setAttribute("pass", password);
            request.setAttribute("error", "Account not exist");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        } else {//account co ton tai
            //check password
            if (loginedUser.getPassword().equals(password) && (loginedUser.getRolesId().getName().equals("admin") || loginedUser.getRolesId().getName().equals("moderator"))) {
                HttpSession session = request.getSession();
                session.setAttribute("phone", loginedUser.getPhoneNumber());
                session.setAttribute("role", loginedUser.getRolesId());
                session.setAttribute("userid", loginedUser.getId());
                session.setAttribute("userId", loginedUser.getId());
                session.setAttribute("roleId", loginadmin);
                response.sendRedirect(request.getContextPath() + "/admin/products/list");
            } else {
                request.setAttribute("phone", phone);
                request.setAttribute("pass", password);
                if (loginedUser.getRolesId().getId().equals("admin") && loginedUser.getRolesId().getName().equals("moderator")) {
                    request.setAttribute("error", "You don't have permission to access this page");
                } else {
                    request.setAttribute("error", "This account is Invalid!");
                }
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            }
        }
        //</editor-fold> 
    }

}
