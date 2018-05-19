/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Feedback;
import Others.StatisticProductCategory;
import SB.CategoryFacadeLocal;
import SB.ProductFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author TRAN HO QUANG
 */
@WebServlet(name = "Statistical_category", urlPatterns = {"/Statisticals/*"})
public class Statistical_category extends HttpServlet {

    @EJB
    private CategoryFacadeLocal categoryFacade;

    @EJB
    private ProductFacadeLocal productFacade;

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
            case "/listcate":
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                Date date = new Date();
                if (year == null && month == null && day == null) {
                    Date today = new Date();
                    date = today;
                } else {
                    date.setYear(Integer.parseInt(year));
                    date.setMonth(Integer.parseInt(month));
                    date.setDate(Integer.parseInt(day));
                }
                List<Others.StatisticProductCategory> listStatisticDetails = new ArrayList<>();
                List<Models.Category> listCategory = categoryFacade.findAll();
                for (Models.Category category : listCategory) {
                    if (category != null) {
                        StatisticProductCategory statistic = new StatisticProductCategory();
                        int categoryId = category.getId();
                        List<Models.Product> listProduct = productFacade.getProductbyCategory(categoryId);
                        List<Models.Product> listProductInSelectedDate = new ArrayList<>();
                        for(Models.Product p : listProductInSelectedDate){
                            if(listProduct.isEmpty()){
                                break;
                            }
                            Date productDate = p.getCreateAt();
                            if(productDate.getDay() == date.getDate() && productDate.getMonth()== date.getMonth() && productDate.getYear()== date.getYear()){
                                listProductInSelectedDate.add(p);
                            }
                        }
                        int postedProduct = listProductInSelectedDate.size();
                        int total = 0;
                            for (Models.Product p : listProductInSelectedDate) {
                                total = total + p.getQuantity();
                            }
                        statistic.setName(category.getName());
                        statistic.setTotalProduct(postedProduct);
                        statistic.setTotalQuantityProduct(total);
                        statistic.setDate(date);
                        listStatisticDetails.add(statistic);
                        System.out.println("lisst" + listStatisticDetails);
                    }
                }
                System.out.println("lisst" + listStatisticDetails);
                request.setAttribute("listStatisticDetails", listStatisticDetails);
                request.getRequestDispatcher("/user/statistical-category.jsp").forward(request, response);
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
            case "/listcate":
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                Date date = new Date();
                if (year == null && month == null && day == null) {
                    Date today = new Date();
                    date = today;
                } else {
                    date.setYear(Integer.parseInt(year));
                    date.setMonth(Integer.parseInt(month));
                    date.setDate(Integer.parseInt(day));
                }
                List<Others.StatisticProductCategory> listStatisticDetails = new ArrayList<>();
                List<Models.Category> listCategory = categoryFacade.findAll();
                for (Models.Category category : listCategory) {
                    StatisticProductCategory statistic = new StatisticProductCategory();
                    int categoryId = category.getId();
                    List<Models.Product> listProduct = productFacade.getProductbyCategory(categoryId);
                    int postingProduct = listProduct.size();
                    int total = 0;
                    for (Models.Product p : listProduct) {
                        total = total + p.getQuantity();
                    }
                    statistic.setName(category.getName());
                    statistic.setTotalProduct(postingProduct);
                    statistic.setTotalQuantityProduct(total);
                    statistic.setDate(date);
                    listStatisticDetails.add(statistic);
                    System.out.println("lisst" + listStatisticDetails);
                }
                System.out.println("lisst" + listStatisticDetails);
                request.setAttribute("listStatisticDetails", listStatisticDetails);
                request.getRequestDispatcher("/user/statistical-category.jsp").forward(request, response);
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
