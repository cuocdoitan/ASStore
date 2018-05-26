/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Product;
import Models.Users;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
@WebServlet(name = "Statistical_user", urlPatterns = {"/user/Statistical_user"})
public class Statistical_user extends HttpServlet {

    @EJB
    private UsersFacadeLocal userFacade;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Statistical_user</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Statistical_user at " + request.getContextPath() + "</h1>");
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
        List<Users> listUsers = userFacade.findAll();
        listUsers.remove(userFacade.getUsersByPhone("0000000000"));
        List<Product> products = productFacade.findAll();
        HashMap userL = new HashMap();
        HashMap<Integer, Integer> userNumberOfProducts = new HashMap<>();
        for (Users user : listUsers) {

            int numberOfProducts = 0;
            for (Product product : products) {
                if (product.getUsersId().getId().equals(user.getId())) {
                    numberOfProducts += 1;
                }
            }
            userNumberOfProducts.put(user.getId(), numberOfProducts);
        }
        
        for (Users user : listUsers) {
            userL.put(user.getId(), user);
        }
        request.setAttribute("users", userL);
        Map<Integer, Integer> r = Util.sortByComparator(userNumberOfProducts);
        request.setAttribute("userNumberOfProducts", r);
        request.getRequestDispatcher("/user/statistical_userproduct.jsp").forward(request, response);
    
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
class MyComparator implements Comparator<Entry<Integer, Integer>> {
  public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
    return o2.getValue().compareTo(o1.getValue());
  }
}

class Util {
  public static Map<Integer, Integer> sortByComparator(
      Map<Integer, Integer> unsortMap) {

    List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(
        unsortMap.entrySet());

    Collections.sort(list, new MyComparator());

    Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
    for (Entry<Integer, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }
}