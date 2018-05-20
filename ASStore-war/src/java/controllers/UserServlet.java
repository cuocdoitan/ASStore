/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.CartDetail;
import Models.Roles;
import Models.Users;
import SB.CartDetailFacadeLocal;
import SB.CartFacadeLocal;
import SB.RolesFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/User/*"})
public class UserServlet extends HttpServlet {

    @EJB
    private RolesFacadeLocal rolesFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private CartFacadeLocal cartFacade;
    
    @EJB
    private CartDetailFacadeLocal cartDetailFacade;
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
                java.util.List<Models.Users> listaccount = usersFacade.getList();
                request.setAttribute("userlist", listaccount);
                request.getRequestDispatcher("/admin/user-accountlist.jsp").forward(request, response);
                break;
            case "/login":
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                break;
            case "/loginadmin":
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
                break;
            case "/detail":
                Models.Users user = usersFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("user", user);
                request.getRequestDispatcher("/admin/user-detail.jsp").forward(request, response);
                break;
            case "/userinfo":
                Models.Users userinfo = usersFacade.find(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("userid", userinfo);
                request.getRequestDispatcher("/user/user-infomation.jsp").forward(request, response);
                break;
            case "/delete":
                Models.Users user1 = usersFacade.find(Integer.parseInt(request.getParameter("id")));
                user1.setEnabled(false);
                usersFacade.edit(user1);
                request.getRequestDispatcher("/User/list").forward(request, response);
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
                registerNewUser(request, response);
                break;
            case "/login":
                login(request, response);
                break;
            case "/loginadmin":
                loginadmin(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void registerNewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String errorMess = "";

        try {
            Models.Users user = new Users();
            user.setId(0);
            boolean error = false;
            if (phone.trim().equals("")) {
                errorMess = errorMess.equals("") ? "phone can't be blank" : errorMess;
                error = true;
            }
            else {
                String pattern = "^\\dre{10,11}";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(phone);
                if (m.matches()) {
                    errorMess = errorMess.equals("") ? "phone must include only number and from 10 - 11 digits" : errorMess;
                    error = true;
                }
                else {
                    request.setAttribute("phone", phone);
                    user.setPhoneNumber(phone);
                }
            }
            
            if (firstname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Firstname can't be blank" : errorMess;
                error = true;
            }else {
                request.setAttribute("firstname", firstname);
                user.setFirstName(firstname);
            }
            if (lastname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Lastname can't be blank" : errorMess;
                error = true;
            }else {
                request.setAttribute("lastname", lastname);
                user.setLastName(lastname);
            }
            if (password.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Password can't be blank" : errorMess;
                error = true;
            }else {
                request.setAttribute("password", password);
                user.setPassword(password);
            }
            if (address.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Address can't be blank" : errorMess;
                error = true;
            }else {
                request.setAttribute("address", address);
                user.setAddress(address);
            }
            if (email.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Email can't be blank" : errorMess;
                error = true;
                
            } else {
                request.setAttribute("email", email);
                user.setEmail(email);
            }
            
            if (error) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            }
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
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        Users loginedUser = usersFacade.getUsersByPhone(phone);
        if (loginedUser == null) {
            //account khong ton tai
            request.setAttribute("phone", phone);
            request.setAttribute("pass", password);
            request.setAttribute("error", "Account not exist");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        } else {//account co ton tai
            //check password
            if (loginedUser.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("phone", loginedUser.getPhoneNumber());
                session.setAttribute("role", loginedUser.getRolesId());
                session.setAttribute("userid", loginedUser.getId());
                session.setAttribute("userId", loginedUser.getId());
                request.setAttribute("user", loginedUser);
                if (cartFacade.findByUserId(loginedUser.getId()) == null) {
                  migrateCartToAccount(session, loginedUser);
                }
                else {
                  migrateSessionCart(session, loginedUser);
                }
                request.getRequestDispatcher("/user/user-information.jsp").forward(request, response);
            } else {
                request.setAttribute("phone", phone);
                request.setAttribute("pass", password);
                request.setAttribute("error", "Password wrong! please try again");
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            }
        }
    }
    
    private void migrateSessionCart(HttpSession sess, Users user) {
      Models.Cart cart = cartFacade.findByUserId(user.getId());
      if (sess.getAttribute("cart") != null) {
        java.util.List<Models.CartDetail> cartDetailSess = (java.util.List<Models.CartDetail>) sess.getAttribute("cart");
        for (Models.CartDetail detail : cartDetailSess) {
          detail.setCartId(cart);
          cartDetailFacade.create(detail);
        }
      }
    }
    
    private void migrateCartToAccount(HttpSession sess, Users user) {
      Models.Cart cart = new Models.Cart();
      cart.setId(0);
      cart.setUsersId(user);
      cartFacade.create(cart);
      if (sess.getAttribute("cart") != null) {
        java.util.List<Models.CartDetail> cartDetailSess = (java.util.List<Models.CartDetail>) sess.getAttribute("cart");
        for (Models.CartDetail detail : cartDetailSess) {
          detail.setCartId(cart);
          cartDetailFacade.create(detail);
        }
      }
    }

    protected void loginadmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            if (loginedUser.getPassword().equals(password) && loginadmin.getName().equals("admin")) {
                HttpSession session = request.getSession();
                session.setAttribute("phone", loginedUser.getPhoneNumber());
                session.setAttribute("role", loginedUser.getRolesId());
                response.sendRedirect(request.getContextPath() + "/admin/products/list");
            } else {
                request.setAttribute("phone", phone);
                request.setAttribute("pass", password);
                request.setAttribute("error", "This account is Invalid!");
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            }
        }
    }

}
