/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Media;
import Models.Product;
import Models.Users;
import SB.AnimeFacadeLocal;
import SB.CategoryFacadeLocal;
import java.util.List;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.io.IOException;
import java.util.logging.Level;
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
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
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
        switch (clientRequest) {
            case "/list":
                java.util.List<Product> listProduct = productFacade.getListProductSortedDesc();
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
                editPage(request, response);
                break;
            case "/repair-product":
                repairPage(request, response);
                break;
            case "/search":
                
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
    
    
    protected void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="go to edit page">
        int productId_edit= Integer.parseInt(request.getParameter("id"));
        Product product_edit = productFacade.find(productId_edit);
        request.setAttribute("product", product_edit);
        request.setAttribute("categories", categoryFacade.findAll());
        String[] arrImage_edit = {"", "", "", ""};
        int i = 0;
        for(Media media : product_edit.getMediaCollection()){
            arrImage_edit[i] = media.getUrlImage();
            i++;
        }
        request.setAttribute("images", arrImage_edit);
        request.getRequestDispatcher("/user/products-edit.jsp").forward(request, response);
        //</editor-fold>
    }
    
    protected void repairPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="go to repair page">
        int productId_repair= Integer.parseInt(request.getParameter("id"));
        Product product_repair = productFacade.find(productId_repair);
        if(product_repair.getStatus() != 2){
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        request.setAttribute("product", productFacade.find(productId_repair));
        request.setAttribute("categories", categoryFacade.findAll());
        String[] arrImage = {"", "", "", ""};
        int j = 0;
        for(Media media : product_repair.getMediaCollection()){
            arrImage[j] = media.getUrlImage();
            j++;
        }
        request.setAttribute("images", arrImage);
        request.getRequestDispatcher("/user/products-repair.jsp").forward(request, response);
        //</editor-fold>
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
        switch (clientRequest) {
            case "/rating":
                
                break;
            case "/insert":
                insertNewProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
            case "/repair-product":
                repairProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
            case "/edit":
                editProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
            case "/delete":
                deleteProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }

    }
    
    

    protected void insertNewProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action insert new product">
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");
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
            uploadProductMedia(request, response, productFacade.find(newProduct.getId()));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during insert product", e.getMessage());
        }
         //</editor-fold>
    }

    protected void uploadProductMedia(HttpServletRequest request, HttpServletResponse response, Product productId)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action upload images">
        String image1 = request.getParameter("image1");
        String image2 = request.getParameter("image2");
        String image3 = request.getParameter("image3");
        String image4 = request.getParameter("image4");
        String[] arrImage = {image1, image2, image3, image4};
        for (int i = 0; i < 4; i++) {
            if (arrImage[i].equals("")) {
                break;
            } else {
                Models.Media media = new Media();
                media.setId(0);
                media.setProductId(productId);
                media.setUrlImage(arrImage[i]);
                try {
                    mediaFacade.create(media);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Problems during saving image name", e.getMessage());
                }
                
            }
        }
        //</editor-fold>
    }
    
    protected void editProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="action edit product">
        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");
        //User
        int userId = Integer.parseInt(request.getParameter("userId"));
        Models.Users user = usersFacade.find(userId);
        //Anime
        String animeName = request.getParameter("anime");
        Models.Anime anime = animeFacade.findAnimeByName(animeName);
        //Category
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Models.Category category = categoryFacade.find(categoryId);
        //Produc before
        Product productBefore = productFacade.find(productId);
        //new Product
        Models.Product editedProduct = new Product(productId, name, description, price, quantity,productBefore.getCreateAt() , true);
        editedProduct.setUsersId(user);
        editedProduct.setAnimeId(anime);
        editedProduct.setCategoryId(category);
        editedProduct.setStatus(Short.parseShort("0"));
        editedProduct.setUpdateAt(new Date());
        editedProduct.setAlertNote("");
        try {
            productFacade.edit(editedProduct);
            editProductMedia(request, response, productFacade.find(editedProduct.getId()));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during edit product", e.getMessage());
        }
        //</editor-fold>
  }
    
    protected void editProductMedia(HttpServletRequest request, HttpServletResponse response, Product productId)
            throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="action edit product image">
        for(Media media : productId.getMediaCollection()){
            mediaFacade.remove(media);
        }
        uploadProductMedia(request, response, productId);
        //</editor-fold>
    }
  
  protected void repairProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="action repair product image">
        int productId = Integer.parseInt(request.getParameter("productId"));
        //Product before
        Product productBefore = productFacade.find(productId);
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");
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
        Models.Product editedProduct = new Product(productId, name, description, price, quantity,productBefore.getCreateAt() , true);
        editedProduct.setUsersId(user);
        editedProduct.setAnimeId(anime);
        editedProduct.setCategoryId(category);
        editedProduct.setStatus(Short.parseShort("0"));
        if(productBefore.getUpdateAt() != null){
            editedProduct.setUpdateAt(new Date());
        }
        editedProduct.setAlertNote("");
        try {
            productFacade.edit(editedProduct);
            editProductMedia(request, response, productFacade.find(editedProduct.getId()));
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during editing product", e.getMessage());
        }
        //</editor-fold>  
  }
  
  protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
      //<editor-fold defaultstate="collapsed" desc="action delete product">
      int id = Integer.parseInt(request.getParameter("id"));
      try {
          Product product = productFacade.find(id);
          product.setEnabled(false);
          productFacade.edit(product);
          
      } catch (Exception e) {
          LOGGER.log(Level.SEVERE, "Problems during deleting product", e.getMessage());
      }
      //</editor-fold> 
  }
  
}
