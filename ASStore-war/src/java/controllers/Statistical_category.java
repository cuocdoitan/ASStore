/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Feedback;
import Models.Product;
import Others.StatisticProductCategory;
import SB.CategoryFacadeLocal;
import SB.ProductFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN HO QUANG
 */
@WebServlet(name = "Statistical_category", urlPatterns = {"/statisticals/*"})
public class Statistical_category extends HttpServlet {

    @EJB
    private CategoryFacadeLocal categoryFacade;

    @EJB
    private ProductFacadeLocal productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute("phone");
        if (phone == null) {
            requestForClientStatistical(request, response);
        } else {
            requestForUserStatistical(request, response);
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

    public void requestForClientStatistical(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/listcate":
                response.sendRedirect(request.getContextPath() + "/User/login");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    public void requestForUserStatistical(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/listcate":
                String yearFrom = request.getParameter("yearFrom");
                String monthFrom = request.getParameter("monthFrom");
                String dayFrom = request.getParameter("dayFrom");
                String dayTo = request.getParameter("dayTo");
                String monthTo = request.getParameter("monthTo");
                Date dateFrom,
                 dateTo;
                if (yearFrom == null && monthFrom == null && dayFrom == null && dayTo == null && monthTo == null) {
                    Date today = new Date();
                    dateFrom = today;
                    dateTo = today;
                } else {
                    if (Integer.parseInt(monthTo) < Integer.parseInt(monthFrom)) {
                        request.setAttribute("error", "Undefined Statistics, Month To lower Month From");
                        request.getRequestDispatcher("/user/statistical-category.jsp").forward(request, response);
                        return;
                    } else {
                        if (Integer.parseInt(dayTo) < Integer.parseInt(dayFrom)) {
                            request.setAttribute("error", "Undefined Statistics, Day To lower Day From");
                            request.getRequestDispatcher("/user/statistical-category.jsp").forward(request, response);
                            return;
                        }
                    }
                    Calendar calendarFrom = Calendar.getInstance();
                    calendarFrom.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayFrom));
                    calendarFrom.set(Calendar.MONTH, Integer.parseInt(monthFrom) - 1);
                    calendarFrom.set(Calendar.YEAR, Integer.parseInt(yearFrom));
                    dateFrom = calendarFrom.getTime();
                    Calendar calendarTo = Calendar.getInstance();
                    calendarTo.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayTo));
                    calendarTo.set(Calendar.MONTH, Integer.parseInt(monthTo) - 1);
                    calendarTo.set(Calendar.YEAR, Integer.parseInt(yearFrom));
                    dateTo = calendarTo.getTime();

                }
                List<Others.StatisticProductCategory> listStatisticDetails = new ArrayList<>();
                String categoryListChart = "";
//                String totalQuantityProductChart = "";
                String totalCurrentChart = "";
                String totalQuantitySoldProductChart = "";
                for (Models.Category category : categoryFacade.findAll()) {
                    categoryListChart += "'" + category.getName() + "',";
                    List<Product> list = productFacade.getProductbyCategoryStatictiscal(category.getId(), dateFrom, dateTo);
                    StatisticProductCategory statisitic = new StatisticProductCategory();

                    int totalQuantityProduct = 0;
                    int totalQuantitySoldProduct = 0;
                    int totalCurrent = 0;

                    for (Models.Product p : list) {
                        int currentQuantityProduct = p.getQuantity();
                        totalCurrent += currentQuantityProduct;

                        int soldQuantityProduct = 0;

                        for (Models.OrdersDetail od : p.getOrdersDetailCollection()) {
                            soldQuantityProduct = od.getQuantity();
                            totalQuantitySoldProduct += soldQuantityProduct;

                        }

                        int initialQuantityProduct = currentQuantityProduct + soldQuantityProduct;
                        totalQuantityProduct += initialQuantityProduct;

                    }
//                    totalQuantityProductChart += totalQuantityProduct + ",";
                    totalQuantitySoldProductChart += totalQuantitySoldProduct + ",";
                    totalCurrentChart += totalCurrent + ",";
                    statisitic.setName(category.getName());
                    statisitic.setTotalProduct(list.size());
                    statisitic.setTotalQuantityProduct(totalQuantityProduct);
                    statisitic.setSoldQuantity(totalQuantitySoldProduct);
                    //
                    statisitic.setDateFrom(dateFrom);
                    statisitic.setDateTo(dateTo);
                    statisitic.setCurrentQuantityProducts(totalCurrent);
                    listStatisticDetails.add(statisitic);
                }
                //
                request.setAttribute("categoryListChart", categoryListChart.substring(0, categoryListChart.length() - 1));
                request.setAttribute("listStatisticDetails", listStatisticDetails);
//                request.setAttribute("totalQuantityProductChart", totalQuantityProductChart.substring(0, totalQuantityProductChart.length() - 1));
                request.setAttribute("totalCurrentChart", totalCurrentChart.substring(0, totalCurrentChart.length() - 1));
                request.setAttribute("totalQuantitySoldProductChart", totalQuantitySoldProductChart.substring(0, totalQuantitySoldProductChart.length() - 1));

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
