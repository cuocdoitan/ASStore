/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zerox
 */
@WebServlet(name = "products", urlPatterns = {"/products/*"})
public class Products extends HttpServlet {

    @EJB
    private MediaFacadeLocal mediaFacade;

    @EJB
    private ProductFacadeLocal productFacade;
    
    
    

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
    response.setContentType("text/html;charset=UTF-8");
    String clientRequest = request.getPathInfo();
    switch(clientRequest){
            case "/list":  
                request.getRequestDispatcher("/user/products-list.jsp").forward(request, response);
                break;
            case "/new-product":
                request.getRequestDispatcher("/user/products-insert.jsp").forward(request, response);
                break;
            case "/details":
                request.getRequestDispatcher("/user/products-details.jsp").forward(request, response);
                break;
            case "/edit":
                request.getRequestDispatcher("/user/products-edit.jsp").forward(request, response);
                break;
            case "/repair-product":
                request.getRequestDispatcher("/user/products-repair.jsp").forward(request, response);
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
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    String clientRequest = request.getPathInfo();
    switch(clientRequest){
            case "/insert":
                break;
            case "/repair-product":
                
                break;
            case "/edit":
                
                break;
            case "/delete":
                
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    
  } 
  
}
