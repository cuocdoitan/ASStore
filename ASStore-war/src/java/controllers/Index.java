/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Product;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
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

/**
 *
 * @author zerox
 */
@WebServlet(name = "index", urlPatterns = {"/index"})
public class Index extends HttpServlet {

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
        System.out.println("getContextPath :" + request.getContextPath());
        System.out.println("getServletPath :" + request.getServletPath());
        System.out.println("getRequestURI :" + request.getRequestURI());
        System.out.println("getLocalName :" + request.getLocalName());
        System.out.println("getPathTranslated :" + request.getPathTranslated());
        System.out.println("getQueryString :" + request.getQueryString());
        System.out.println("getRemoteUser :" + request.getRemoteUser());
        List<Models.Product> listProductIndex = new ArrayList<>();
        int numberOfProduct = 0;
        for(Product product : productFacade.getListProductSortedDesc()){
            listProductIndex.add(product);
            numberOfProduct++;
            if(numberOfProduct==8){
                break;
            }
        }
        request.setAttribute("images", mediaFacade.getFirstImageFromListProduct(listProductIndex));
        request.setAttribute("listProduct", listProductIndex);
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
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
        processRequest(request, response);
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
