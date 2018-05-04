/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Product;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tien Phat
 */
@WebServlet(name = "products_admin", urlPatterns = {"/admin/products/*"})
public class Products_admin extends HttpServlet {

    @EJB
    private MediaFacadeLocal mediaFacade;

    @EJB
    private ProductFacadeLocal productFacade;


    
    
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
        switch(clientRequest){
            case "/list":
                List<Product> listProduct = productFacade.findAll();
                request.setAttribute("listImages", mediaFacade.getImagesFromListProduct(listProduct));
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("/admin/products-list.jsp").forward(request, response);
                break;
            case "/approving-list":
                List<Product> listApprovingProduct = productFacade.getListApprovingProduct();
                request.setAttribute("listImages", mediaFacade.getImagesFromListProduct(listApprovingProduct));
                request.setAttribute("listApprovingProduct", listApprovingProduct);
                request.getRequestDispatcher("/admin/products-approving-list.jsp").forward(request, response);
                break;
            case "/deny":
                int productId_deny = Integer.parseInt(request.getParameter("id"));
                Product product_deny = productFacade.find(productId_deny);
                request.setAttribute("images", mediaFacade.getImagesFromProduct(product_deny));
                request.setAttribute("product", product_deny);
                request.getRequestDispatcher("/admin/products-deny.jsp").forward(request, response);
                break;
            case "/detail":
                int productId_detail = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("product", productFacade.find(productId_detail));
                request.getRequestDispatcher("/admin/products-details.jsp").forward(request, response);
                break;
            case "/search":
                
                request.getRequestDispatcher("/admin/products-list.jsp").forward(request, response);
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
