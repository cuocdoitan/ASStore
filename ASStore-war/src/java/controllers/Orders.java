/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.MediaFacadeLocal;
import SB.OrdersDetailFacadeLocal;
import SB.OrdersFacadeLocal;
import SB.ProductFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author zerox
 */
@WebServlet(name = "Orders", urlPatterns = {"/orders/*"})
public class Orders extends HttpServlet {

  @EJB
  private OrdersFacadeLocal orderFacade;
  @EJB
  private OrdersDetailFacadeLocal orderDetailFacade;
  @EJB
  private MediaFacadeLocal mediaFacade;

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
        List<Models.Orders> orders = orderFacade.findAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/user/orders.jsp").forward(request, response);
        break;
      case "/details":
        int orderId = Integer.parseInt(request.getParameter("order"));
        List<Models.OrdersDetail> details = orderDetailFacade.findByOrder(orderId);
        HashMap images = new HashMap();
        BigDecimal total = new BigDecimal(0);
        for (Models.OrdersDetail detail : details) {
          total = total.add(detail.getUnitPrice().multiply(new BigDecimal(detail.getQuantity())));
          images.put(detail.getProductId().getId(), mediaFacade.getFirstImageFromProduct(detail.getProductId()));
        }
        request.setAttribute("orderTotal", total);
        request.setAttribute("images", images);
        request.setAttribute("details", details);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("/user/orders-details.jsp").forward(request, response);
        break;
      case "/delete":
        orderId = Integer.parseInt(request.getParameter("order"));
        orderFacade.delete(orderId);
        response.sendRedirect("list");
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
