/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Product;
import Models.Users;
import SB.AnimeFacadeLocal;
import SB.CategoryFacadeLocal;
import java.util.List;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
public class ProductServlet extends HttpServlet {
    
    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private CategoryFacadeLocal categoryFacade;

    @EJB
    private AnimeFacadeLocal animeFacade;

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
                java.util.List<Product> listProduct = productFacade.getListExistingProduct();
                request.setAttribute("images", mediaFacade.getFirstImageFromListProduct(listProduct));
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("/user/products-list.jsp").forward(request, response);
                break;
            case "/new-product":
                request.setAttribute("categories", categoryFacade.findAll());
                request.getRequestDispatcher("/user/products-insert.jsp").forward(request, response);
                break;
            case "/details":
                int productId_detail = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("product", productFacade.find(productId_detail));
                //request.setAttribute("images", mediaFacade.getImagesFromProduct(productFacade.find(productId_detail)));
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
                insertNewProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
            case "/repair-product":
                repairProduct(request, response);
                break;
            case "/edit":
                editProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    
  }
  
  protected void insertNewProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
    String name = request.getParameter("name");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    BigDecimal price = new BigDecimal(request.getParameter("price"));
    String description= request.getParameter("description");
    //User
    int userId = Integer.parseInt(request.getParameter("userId"));
    Models.Users user = usersFacade.find(userId);
    //Anime
    String animeName = request.getParameter("anime");
    Models.Anime anime = animeFacade.findAnimeByName(animeName);
    //Category
    int categoryId = Integer.parseInt(request.getParameter("category"));
    Models.Category category = categoryFacade.find(categoryId);
    //new Product
    Models.Product newProduct = new Product(0, name, description, price, quantity, new Date(), true);
    newProduct.setUsersId(user);
    newProduct.setAnimeId(anime);
    newProduct.setCategoryId(category);
    newProduct.setStatus(Short.parseShort("0"));
    newProduct.setAlertNote("");
      try {
          productFacade.create(newProduct);
      } catch (Exception e) {
          System.out.println("=================================");
          System.out.println(e.toString());
      }
    
  }
  
  protected void editProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
      
  }
  
  protected void repairProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
      
  }
  
  protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
      
  }
  
}
