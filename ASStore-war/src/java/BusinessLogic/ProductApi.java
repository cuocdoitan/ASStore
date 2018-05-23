/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import Models.Anime;
import Models.Product;
import SB.CouponsFacadeLocal;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author zerox
 */
@WebServlet(name = "ProductApi", urlPatterns = {"/productApi"})
public class ProductApi extends HttpServlet {

  @EJB
  private ProductFacadeLocal productFacade;
  @EJB
  private MediaFacadeLocal mediaFacade;
  @EJB
  private UsersFacadeLocal userFacade;
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
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    HttpSession sess = request.getSession();
    List<Product> products = productFacade.getListProductOfUser(userFacade.find(sess.getAttribute("userId")));

    JSONArray productsJSON = new JSONArray();

    if (request.getParameterMap().containsKey("name")) {
      final String name = request.getParameter("name").trim();
      Collections.sort(products, new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
          return -Integer.compare(getSimilarity(o1, name), getSimilarity(o2, name));
        }

        private int getSimilarity(Product product, String keyword) {
          return product.getName().toLowerCase().length() - product.getName().toLowerCase().replace(keyword, "").length();
        }
      });
      
      for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
        Product product = iterator.next();
        int similarity = product.getName().toLowerCase().length() - product.getName().toLowerCase().replace(name, "").length();
        
        if (similarity == 0) {
          iterator.remove();
        }
      }
    }
    
    int limit = 4;
    for (Product product : products) {
      if (couponsFacade.findByProduct(product) == null) {
        JSONObject productJSON = new JSONObject();
        if (limit > 0) {
          productJSON.put("id", product.getId());
          productJSON.put("name", product.getName());
          productJSON.put("picture", mediaFacade.getFirstImageFromProduct(product));
          productsJSON.add(productJSON);
          limit--;
        }
      }
      else {
        break;
      }
    }
    out.print(productsJSON);
    out.flush();
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
