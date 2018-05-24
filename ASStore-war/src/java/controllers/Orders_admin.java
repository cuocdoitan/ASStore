/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.MediaFacadeLocal;
import SB.OrdersDetailFacadeLocal;
import SB.OrdersFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "Orders_admin", urlPatterns = {"/admin/orders/*"})
public class Orders_admin extends HttpServlet {

  @EJB
  private OrdersFacadeLocal orderFacade;
  @EJB
  private OrdersDetailFacadeLocal orderDetailFacade;
  @EJB
  private MediaFacadeLocal mediaFacade;
  @EJB
  private UsersFacadeLocal userFacade;

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
      out.println("<title>Servlet Orders_admin</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet Orders_admin at " + request.getContextPath() + "</h1>");
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
    Models.Users user = userFacade.getGuestUser();
    if (sess.getAttribute("userId") != null) {
      user = userFacade.find((int)sess.getAttribute("userId"));
    }
    switch (clientRequest) {
      case "/list":
        if (!user.getRolesId().getName().trim().equals("admin")){
          request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
          return;
        }
        java.util.List<Models.Orders> orders = orderFacade.findAll();
        ArrayList<Models.Orders> filteredOrders = new ArrayList<>();
        if (request.getParameter("show_all") == null) {
          if (request.getParameter("show_delivering") != null && request.getParameter("show_delivered") == null) {
            for (Models.Orders order : orders) {
              if (!order.getStatus()) {
                filteredOrders.add(order);
              }
            }
          } else if (request.getParameter("show_delivering") == null && request.getParameter("show_delivered") != null) {
            for (Models.Orders order : orders) {
              if (order.getStatus()) {
                filteredOrders.add(order);
              }
            }
          } else {
            for (Models.Orders order : orders) {
              filteredOrders.add(order);
            }
          }

        } else {
          for (Models.Orders order : orders) {
            filteredOrders.add(order);
          }
        }
        request.setAttribute("orders", filteredOrders);
        HashMap users = new HashMap();
        for (Models.Orders order : filteredOrders) {
          users.put(order.getId(), order.getUsersId());
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
        break;
      case "/accept":
        int orderId = Integer.parseInt(request.getParameter("order"));
        Models.Orders order = orderFacade.find(orderId);
        order.setDelivering(true);
        orderFacade.edit(order);
        response.sendRedirect(request.getContextPath() + "/admin/orders/list");
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
