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
import static Models.Users_.email;
import java.util.List;
import SB.ProductFacadeLocal;
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
    private ProductFacadeLocal productFacade;

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
        HttpSession sess = request.getSession();
        String clientRequest = request.getPathInfo();
//        Roles role = (Roles)sess.getAttribute("role");
        switch (clientRequest) {
            case "/list":
                java.util.List<Models.Users> listaccount = usersFacade.getList();
                request.setAttribute("userlist", listaccount);
                request.getRequestDispatcher("/admin/user-accountlist.jsp").forward(request, response);
                break;
            case "/login":
                if(sess.getAttribute("userid") != null) {
                    response.sendRedirect(request.getContextPath() + "/index");
                    return;
                }
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                break;
            case "/loginadmin":
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
                break;
            case "/detail":
                Models.Users user = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
                List<Models.Product> listAvailableProduct = productFacade.getListAvailableProduct_User(user);
                List<Models.Product> listCheckingProduct = productFacade.getListCheckingProduct_User(user);
                List<Models.Product> listUnavailableProduct = productFacade.getListUnavailableProduct_User(user);
                request.setAttribute("listAvailableProduct", listAvailableProduct);
                request.setAttribute("listCheckingProduct", listCheckingProduct);
                request.setAttribute("listUnavailableProduct", listUnavailableProduct);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user/user-information.jsp").forward(request, response);
                break;
            case "/userinfo":
                Models.Users userinfo = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
                request.setAttribute("userid", userinfo);
                request.getRequestDispatcher("/admin/user-detail.jsp").forward(request, response);
                break;
            case "/update":
                //HttpSession sess = request.getSession();
                Models.Users userupdate = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
                request.setAttribute("user", userupdate);
                request.getRequestDispatcher("/user/user-information-update.jsp").forward(request, response);
                break;
            case "/updatephone":
                //HttpSession sess = request.getSession();
                Models.Users userupdatephone = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
                request.setAttribute("user", userupdatephone);
                request.getRequestDispatcher("/user/user-information-updatephone.jsp").forward(request, response);
                break;
            case "/updatepass":
                //HttpSession sess = request.getSession();
                Models.Users userupdatepass = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
                request.setAttribute("user", userupdatepass);
                request.getRequestDispatcher("/user/user-information-changepass.jsp").forward(request, response);
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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
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
            case "/Updateuser":
                Updateuser(request, response);
                break;
            case "/Updateuserphone":
                Updateuserphone(request, response);
                break;
            case "/Updateuserpass":
                Updateuserpass(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void registerNewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="register new user">
        String phone = request.getParameter("phone");
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String inputConfirmpass = request.getParameter("confirm_password");
        String errorMess = "";
        Models.Users exitsUser = usersFacade.getUsersByPhone(phone);
        try {
            Models.Users user = new Users();
            user.setId(0);
            boolean error = false;

            if (phone.trim().equals("")) {
                errorMess = errorMess.equals("") ? "phone can't be blank" : errorMess;
                error = true;
            } else {
                if (exitsUser != null) {
                    errorMess = errorMess.equals("") ? "phone exits" : errorMess;
                    error = true;
                } else {
                    String pattern = "^0(1|8|9)\\d{8,9}$";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(phone);
                    if (!m.matches()) {
                        errorMess = errorMess.equals("") ? "phone must include only number and from 10 - 11 digits, please try again! " : errorMess;
                        error = true;
                    } else {
                        request.setAttribute("phone", phone);
                        user.setPhoneNumber(phone);
                    }
                }
            }

            if (firstname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Firstname can't be blank" : errorMess;
                error = true;
            } else {
                request.setAttribute("firstname", firstname);
                user.setFirstName(firstname);
            }
            if (lastname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Lastname can't be blank" : errorMess;
                error = true;
            } else {
                request.setAttribute("lastname", lastname);
                user.setLastName(lastname);
            }
            String pattern = "^\\w{6,16}";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(password);
            if (!m.matches()) {
                errorMess = errorMess.equals("") ? "Password must from 6 - 16 charecter, please try again! " : errorMess;
                error = true;
            } else {
                request.setAttribute("password", password);
                user.setPassword(password);
            }
            if (!password.equals(inputConfirmpass)) {
                errorMess = errorMess.equals("") ? "comfirm password isn't the same with old pass" : errorMess;
                error = true;
            }
            if (address.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Address can't be blank" : errorMess;
                error = true;
            } else {
                request.setAttribute("address", address);
                user.setAddress(address);
            }
            if (email.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Email can't be blank" : errorMess;
                error = true;

            } else {
                Models.Users userEmail = usersFacade.getUserByEmail(email);
                if (userEmail != null) {
                    // da co dua xai email nay
                    errorMess = errorMess.equals("") ? "Email is exists!" : errorMess;
                    error = true;
                } else {
                    request.setAttribute("email", email);
                    user.setEmail(email); //email anh muốn bắt trùng thì 
                }
            }

            if (error) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                //return;
            }
            user.setCreateAt(new Date());
            user.setEnabled(true);
            user.setNumberOfNotification(0);
            user.setRolesId(rolesFacade.find(3));
            usersFacade.create(user);
        } catch (Exception e) {
            System.out.println("==============================");
            System.out.println("Exception : ");
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/User/create");
        // <editor-fold>
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="login user">
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
                session.setAttribute("user", loginedUser);
                request.setAttribute("user", loginedUser);
                response.sendRedirect(request.getContextPath() + "/User/detail");
            } else {
                request.setAttribute("phone", phone);
                request.setAttribute("pass", password);
                request.setAttribute("error", "Password wrong! please try again");
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            }
        }
        //<editor-fold>
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
        // <editor-fold defaultstate="collapsed" desc="login admin">
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
                response.sendRedirect(request.getContextPath() + "/User/userinfo");
            } else {
                request.setAttribute("phone", phone);
                request.setAttribute("pass", password);
                if (loginedUser.getRolesId().getName().equals("admin") && loginedUser.getRolesId().getName().equals("moderator")) {
                    request.setAttribute("error", "You don't have permission to access this page");
                } else {
                    request.setAttribute("error", "This account is Invalid!");
                }
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            }
        }
    }

    protected void Updateuser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="update user">
        String inputfirstname = request.getParameter("firstname");
        String inputlastname = request.getParameter("lastname");
        String inputaddress = request.getParameter("address");
        String inputemail = request.getParameter("email");
        HttpSession sess = request.getSession();
        Models.Users user = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
        Models.Users userEmail = usersFacade.getUserByEmail(inputemail);
        String errorMess = "";
        try {
            boolean error = false;
            //validate firstname
            if (inputfirstname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Firstname can't be blank" : errorMess;
                error = true;
            }
            if (inputlastname.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Lastname can't be blank" : errorMess;
                error = true;
            } 
            
//            if (userEmail != null) {
//                errorMess = errorMess.equals("") ? "Email exits" : errorMess;
//                error = true;
//            }
           
            if (error == true) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/user/user-information-update.jsp").forward(request, response);
            }
            else {
                user.setFirstName(inputfirstname);
                user.setLastName(inputlastname);
//                user.setEmail(inputemail);
                user.setAddress(inputaddress);
                usersFacade.edit(user);
                response.sendRedirect(request.getContextPath() + "/User/detail");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void Updateuserphone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapsed" desc="update user phone">
        HttpSession sess = request.getSession();
        String inputPass = request.getParameter("pass");
        String inputNewPhone = request.getParameter("new_phone");
        String inputConfirmPhone = request.getParameter("confirm_new_phone");
        Models.Users user = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
        String errorMess = "";
        Models.Users exitsUser = usersFacade.getUsersByPhone(inputNewPhone);
        try {
            boolean error = false;
            //validate password
            if (!user.getPassword().equals(inputPass)) {
                errorMess = errorMess.equals("") ? "Wrong password" : errorMess;
                error = true;
            }
            //validate phone
            String pattern = "^0(1|8|9)\\d{8,9}$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(inputNewPhone);
            if (!m.matches()) {
                errorMess = errorMess.equals("") ? "phone must include only number and from 10 - 11 digits, please try again! " : errorMess;
                error = true;
            }
            if (exitsUser != null) {
                errorMess = errorMess.equals("") ? "phone exits" : errorMess;
                error = true;
            }
            //validate comfirm phone
            if (!inputConfirmPhone.equals(inputNewPhone)) {
                errorMess = errorMess.equals("") ? "comfirm phone isn't the same with new phone" : errorMess;
                error = true;
            }
            //check error
            if (error == true) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/user/user-information-updatephone.jsp").forward(request, response);
            } else {
                user.setPhoneNumber(inputNewPhone);
                usersFacade.edit(user);
                response.sendRedirect(request.getContextPath() + "/User/detail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void Updateuserpass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // <editor-fold defaultstate="collapse+d" desc="update user phone">
        HttpSession sess = request.getSession();
        String inputPass = request.getParameter("old_pass");
        String inputNewpass = request.getParameter("new_pass");
        String inputConfirmpass = request.getParameter("confirm_new_pass");
        Models.Users user = usersFacade.find(Integer.parseInt(sess.getAttribute("userid").toString()));
        String errorMess = "";
//        Models.Users exitsUser = usersFacade.getUsersByPhone(inputNewPhone);
        try {
            boolean error = false;
            //validate password
            if (!user.getPassword().equals(inputPass)) {
                errorMess = errorMess.equals("") ? "Wrong old password" : errorMess;
                error = true;
            }
            String pattern = "^\\w{6,16}";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(inputNewpass);
            if (!m.matches()) {
                errorMess = errorMess.equals("") ? "Password must from 6 - 16 charecter, please try again! " : errorMess;
                error = true;
            }
            //validate comfirm pass
            if (!inputConfirmpass.equals(inputNewpass)) {
                errorMess = errorMess.equals("") ? "comfirm password isn't the same with New password" : errorMess;
                error = true;
            }
            //check error
            if (error == true) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/user/user-information-changepass.jsp").forward(request, response);
            } else {
                user.setPassword(inputNewpass);
                usersFacade.edit(user);
                response.sendRedirect(request.getContextPath() + "/User/detail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>
}
