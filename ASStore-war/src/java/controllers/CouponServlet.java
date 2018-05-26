/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Coupons;
import SB.CouponsFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zerox
 */
@WebServlet(name = "CouponServlet", urlPatterns = {"/coupon/*"})
public class CouponServlet extends HttpServlet {

  @EJB
  private UsersFacadeLocal userFacade;
  @EJB
  private ProductFacadeLocal productFacade;
  @EJB
  private CouponsFacadeLocal couponsFacade;

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
      out.println("<title>Servlet CouponServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet CouponServlet at " + request.getContextPath() + "</h1>");
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
    HttpSession sess = request.getSession();
    switch (clientRequest) {
      case "/create":
        if (sess.getAttribute("userId") == null) {
          request.getRequestDispatcher("/user/login.jsp").forward(request, response);
          return;
        }
        request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
        break;
      case "/list":
        if (sess.getAttribute("userId") == null) {
          request.getRequestDispatcher("/user/login.jsp").forward(request, response);
          return;
        }
        java.util.List<Coupons> all = couponsFacade.findEnableCouponsOfUser((int) sess.getAttribute("userId"));
        request.setAttribute("coupons", all);
        request.getRequestDispatcher("/user/coupon-list.jsp").forward(request, response);
        break;
      case "/remove":
        int couponId = Integer.parseInt(request.getParameter("id"));
        if (sess.getAttribute("userId") == null) {
          request.getRequestDispatcher("/user/login.jsp").forward(request, response);
          return;
        }
        int userId = (int) sess.getAttribute("userId");
        Coupons co = couponsFacade.find(couponId);
        if (co.getUserId() == userId) {
          co.setEnabled(false);
          couponsFacade.edit(co);
          response.sendRedirect(request.getContextPath() + "/coupon/list");
        }
        else {
          response.sendRedirect(request.getContextPath() + "/coupon/list");
        }
        break;
      case "/edit":
        if (request.getParameter("id") == null) {
          response.sendRedirect(request.getContextPath() + "/coupon/list");
          return;
        }
        int couId = Integer.parseInt(request.getParameter("id"));
        Models.Coupons cos = couponsFacade.find(couId);
        request.setAttribute("cId", couId);
        request.setAttribute("product", cos.getProductId().getName());
        request.setAttribute("productId", cos.getProductId().getId());
        request.setAttribute("coupon", cos.getCoupon());
        request.setAttribute("expire", cos.getExpireDate());
        request.setAttribute("percentage", cos.getPercentage());
        request.getRequestDispatcher("/user/coupon-edit.jsp").forward(request, response);
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
    HttpSession sess = request.getSession();
    switch (clientRequest) {
      case "/create":
        String product = request.getParameter("product");
        String coupon = request.getParameter("coupon");
        String expire = request.getParameter("expire");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int percentage = Integer.parseInt(request.getParameter("percentage"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Models.Coupons coupond = new Models.Coupons();
        request.setAttribute("expire", expire);
        request.setAttribute("coupon", coupon);
        request.setAttribute("percentage", percentage);
        request.setAttribute("product", product);
        request.setAttribute("productId", productId);
        if (coupon.length() > 20 || coupon.length() < 3) {
          request.setAttribute("coupon", "");
          request.setAttribute("error", "Coupon code must less than 20 characters and at least 3 characters");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
          return;
        }
        
        if (coupon.trim().equals("")) {
          request.setAttribute("coupon", "");
          request.setAttribute("error", "Coupon code can't be blank");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
          return;
        }
        
        if (couponsFacade.findByCoupon(coupon) != null && couponsFacade.findByCoupon(coupon).getEnabled() == true) {
          request.setAttribute("coupon", "");
          request.setAttribute("error", "That coupon has been used!");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
          return;
        }

        coupond.setCoupon(coupon);
        try {
          Date ex = formatter.parse(expire);
          if (ex.before(new Date())) {
            request.setAttribute("expire", "");
            request.setAttribute("error", "Coupon expire date must not be less than today");
            request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
            return;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        coupond.setExpireDate(expire);
        if (percentage > 90 || percentage < 10) {
          request.setAttribute("percentage", "");
          request.setAttribute("error", "Coupon discount percentage must less than 90 and greater than 10%");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
          return;
        }

        coupond.setPercentage(percentage);
        coupond.setUserId((int) sess.getAttribute("userId"));
        coupond.setProductId(productFacade.find(productId));
        coupond.setEnabled(true);

        coupond.setId(0);

        request.setAttribute("expire", "");
        request.setAttribute("coupon", "");
        request.setAttribute("percentage", "");
        request.setAttribute("product", "");
        request.setAttribute("productId", "");
        try {
          couponsFacade.create(coupond);
          request.setAttribute("mess", "Create coupon success !");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
        } catch (Exception e) {
          e.printStackTrace();
          request.setAttribute("error", "Create failed !");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
        }
        break;
      case "/edit":
        int ecouponId = Integer.parseInt(request.getParameter("id"));
        String eproduct = request.getParameter("product");
        String ecoupon = request.getParameter("coupon");
        String eexpire = request.getParameter("expire");
        int eproductId = Integer.parseInt(request.getParameter("productId"));
        int epercentage = Integer.parseInt(request.getParameter("percentage"));
        SimpleDateFormat eformatter = new SimpleDateFormat("yyyy-MM-dd");
        Models.Coupons ecoupond = couponsFacade.find(ecouponId);
        request.setAttribute("cId", ecouponId);
        request.setAttribute("expire", ecoupond.getExpireDate());
        request.setAttribute("coupon", ecoupond.getCoupon());
        request.setAttribute("percentage", ecoupond.getPercentage());
        request.setAttribute("product", ecoupond.getProductId().getName());
        request.setAttribute("productId", ecoupond.getProductId().getId());
        if (ecoupon.length() > 20 || ecoupon.length() < 3) {
          request.setAttribute("error", "Coupon code must less than 20 characters and at least 3 characters");
          request.getRequestDispatcher("/user/coupon-edit.jsp").forward(request, response);
          return;
        }
        
        if (ecoupon.trim().equals("")) {
          request.setAttribute("coupon", "");
          request.setAttribute("error", "Coupon code can't be blank");
          request.getRequestDispatcher("/user/coupon-create.jsp").forward(request, response);
          return;
        }

        ecoupond.setCoupon(ecoupon);
        try {
          Date ex = eformatter.parse(eexpire);
          if (ex.before(new Date())) {
            request.setAttribute("error", "Coupon expire date must not be less than today");
            request.getRequestDispatcher("/user/coupon-edit.jsp").forward(request, response);
            return;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        ecoupond.setExpireDate(eexpire);
        if (epercentage > 90 || epercentage < 10) {
          request.setAttribute("error", "Coupon discount percentage must less than 90 and greater than 10%");
          request.getRequestDispatcher("/user/coupon-edit.jsp").forward(request, response);
          return;
        }

        ecoupond.setPercentage(epercentage);
        ecoupond.setUserId((int) sess.getAttribute("userId"));
        ecoupond.setProductId(productFacade.find(eproductId));
        try {
          couponsFacade.edit(ecoupond);
          request.setAttribute("mess", "Edit coupon success !");
          response.sendRedirect(request.getContextPath() + "/coupon/list");
        } catch (Exception e) {
          e.printStackTrace();
          request.setAttribute("error", "Create failed !");
          response.sendRedirect(request.getContextPath() + "/coupon/edit?id=" + ecouponId);
        }
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
