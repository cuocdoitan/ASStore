/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Media;
import Models.Product;
import Models.Users;
import Models.ProductRating;
import SB.AnimeFacadeLocal;
import SB.CategoryFacadeLocal;
import java.util.List;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import SB.ProductRatingFacadeLocal;
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
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;

/**
 *
 * @author zerox
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductRatingFacadeLocal productRatingFacade;

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
        HttpSession session = request.getSession();
        switch (clientRequest) {
            case "/list":
                listPage(request, response);
                break;
            case "/new-product":
                if (session.getAttribute("role") == null) {
                    response.sendRedirect(request.getContextPath() + "/User/login");
                    return;
                }
                createProductPage(request, response);
                break;
            case "/details":
                detailsPage(request, response);
                break;
            case "/edit":
                if (session.getAttribute("role") == null) {
                    response.sendRedirect(request.getContextPath() + "/User/login");
                    return;
                }
                editPage(request, response);
                break;
            case "/repair-product":
                if (session.getAttribute("role") == null) {
                    response.sendRedirect(request.getContextPath() + "/User/login");
                    return;
                }
                repairPage(request, response);
                break;
            case "/validateProductSubmit":
                validateProductSubmit(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void listPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="show list product and list searched product">
        String productName = request.getParameter("productName");
        String animeName = request.getParameter("anime_name");
        String category = request.getParameter("category");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        if (productName == null && animeName == null && category == null && minPrice == null && maxPrice == null) {
            java.util.List<Product> listProduct = productFacade.getListProductSortedDesc();
            request.setAttribute("images", mediaFacade.getFirstImageFromListProduct(listProduct));
            request.setAttribute("categories", categoryFacade.findAll());
            request.setAttribute("listProduct", listProduct);
            request.getRequestDispatcher("/user/products-list.jsp").forward(request, response);
        } else {
            boolean error = false;
            String sProductName = productName;
            Models.Anime sAnimeId = null;
            Models.Category sCategoryId = null;
            BigDecimal sMinPrice = null;
            BigDecimal sMaxPrice = null;
            //anime
            if (animeName != null) {
                if (!animeName.equals("")) {
                    Models.Anime anime = animeFacade.findAnimeByName(animeName);
                    if (anime == null) {
                        request.setAttribute("animeError", "This anime isn't exist");
                        error = true;
                    } else {
                        sAnimeId = anime;
                    }
                }
            }

            //category
            if (category != null) {
                if (!category.equals("")) {
                    sCategoryId = categoryFacade.find(Integer.parseInt(category));
                }
            }

            //min price
            if (minPrice != null) {
                if (!minPrice.equals("")) {
                    BigDecimal bigminPrice = new BigDecimal(minPrice);
                    if (bigminPrice.compareTo(BigDecimal.ZERO) < 0) {
                        request.setAttribute("minPriceError", "Price can't be below 0");
                        error = true;
                    } else {
                        sMinPrice = bigminPrice;
                    }

                }
            }

            //max price
            if (maxPrice != null) {
                if (!maxPrice.equals("")) {
                    BigDecimal bigmaxPrice = new BigDecimal(maxPrice);
                    if (bigmaxPrice.compareTo(BigDecimal.ZERO) < 0) {
                        request.setAttribute("maxPriceError", "Price can't be below 0");
                        error = true;
                    } else {
                        sMaxPrice = bigmaxPrice;
                    }
                }
            }

            if (sMinPrice != null && sMaxPrice != null) {
                if (sMaxPrice.compareTo(sMinPrice) == -1) {
                    request.setAttribute("comparePriceError", "Min price can't beow max price");
                    error = true;
                }
            }

            if (error == true) {
                java.util.List<Product> listProduct = productFacade.getListProductSortedDesc();
                request.setAttribute("images", mediaFacade.getFirstImageFromListProduct(listProduct));
                request.setAttribute("listProduct", listProduct);
                return;
            } else {
                java.util.List<Product> listProduct = productFacade.searchProduct(sProductName, sAnimeId, sCategoryId, sMinPrice, sMaxPrice);
                request.setAttribute("images", mediaFacade.getFirstImageFromListProduct(listProduct));
                request.setAttribute("listProduct", listProduct);

            }
            request.setAttribute("vproductName", productName);
            request.setAttribute("vanimeName", animeName);
            request.setAttribute("vcategory", category);
            request.setAttribute("vminPrice", minPrice);
            request.setAttribute("vmaxPrice", maxPrice);
            request.setAttribute("categories", categoryFacade.findAll());
            request.getRequestDispatcher("/user/products-list.jsp").forward(request, response);

        }

        //</editor-fold>
    }

    protected void createProductPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to create product page">
        request.setAttribute("categories", categoryFacade.findAll());
        request.getRequestDispatcher("/user/products-insert.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void detailsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to details page">
        int productId_detail = Integer.parseInt(request.getParameter("id"));
        Product product = productFacade.find(productId_detail);
        request.setAttribute("product", product);
        request.setAttribute("similarProducts", productFacade.getRandomProductSameAnime(product));
        Integer sessionUserId = (Integer) request.getSession().getAttribute("userid");
        if(sessionUserId != null){
            for(ProductRating productRating : product.getProductRatingCollection()){
                if(productRating.getUsersId().getId() == sessionUserId){
                    request.setAttribute("ratedStars", productRating.getRating());
                    break;
                }
            }
            
        }
        request.getRequestDispatcher("/user/products-details.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to edit page">
        HttpSession session = request.getSession();
        int sUserId = (int) session.getAttribute("userid");

        int productId_edit = Integer.parseInt(request.getParameter("id"));
        Product product_edit = productFacade.find(productId_edit);

        if (product_edit.getUsersId().getId() != sUserId) {
            response.sendRedirect(request.getContextPath() + "/User/login");
            return;
        }

        request.setAttribute("product", product_edit);
        request.setAttribute("categories", categoryFacade.findAll());
        String[] arrImage_edit = {"", "", "", ""};
        int i = 0;
        for (Media media : product_edit.getMediaCollection()) {
            arrImage_edit[i] = media.getUrlImage();
            i++;
        }
        request.setAttribute("images", arrImage_edit);
        request.getRequestDispatcher("/user/products-edit.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void repairPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to repair page">
        HttpSession session = request.getSession();
        int sUserId = (int) session.getAttribute("userid");

        int productId_repair = Integer.parseInt(request.getParameter("id"));
        Product product_repair = productFacade.find(productId_repair);
        if (product_repair.getUsersId().getId() != sUserId) {
            response.sendRedirect(request.getContextPath() + "/User/login");
            return;
        }

        if (product_repair.getStatus() != 2) {
            response.sendRedirect(request.getHeader("referer"));
            return;
        }
        request.setAttribute("product", productFacade.find(productId_repair));
        request.setAttribute("categories", categoryFacade.findAll());
        String[] arrImage = {"", "", "", ""};
        int j = 0;
        for (Media media : product_repair.getMediaCollection()) {
            arrImage[j] = media.getUrlImage();
            j++;
        }
        request.setAttribute("images", arrImage);
        request.getRequestDispatcher("/user/products-repair.jsp").forward(request, response);
        //</editor-fold>
    }

    protected void validateProductSubmit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to repair page">
        response.setContentType("application/json");
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        String anime = request.getParameter("anime");
        String description = request.getParameter("description");
        String image1 = request.getParameter("image1");
        

        String errName = "";
        String errQuantity = "";
        String errPrice = "";
        String errAnime = "";
        String errDescription = "";
        String errImage = "";
        
        

        //check name
        if (name.trim().equals("")) {
            errName = errName.equals("") ? "This field cannot be blank" : errName;
        }
        if (name.length() > 50) {
            errName = errName.equals("") ? "Can not be more than 50 characters" : errName;
        }

        //check quantity
        if (quantity.trim().equals("")) {
            errQuantity = errQuantity.equals("") ? "This field cannot be blank" : errQuantity;
        }
        if (quantity.length() > 4) {
            errQuantity = errQuantity.equals("") ? "Can only input no more than 4 digits" : errQuantity;
        }
        boolean isInt = true;
        Integer intQuantity = null;
        try {
            intQuantity = Integer.parseInt(quantity);
        } catch (Exception e) {
            errQuantity = errQuantity.equals("") ? "Invalid number" : errQuantity;
            isInt = false;
        }
        if(isInt){
            if(intQuantity <= 0){
                errQuantity = errQuantity.equals("") ? "Must be more than 0" : errQuantity;
            }
        }
        
        //check price
        if (price.trim().equals("")) {
            errPrice = errPrice.equals("") ? "This field cannot be blank" : errPrice;
        }
        boolean isBC = true;
        BigDecimal bc = null;
        try {
            bc = new BigDecimal(price);
        } catch (Exception e) {
            errPrice = errPrice.equals("") ? "Invalid input number" : errPrice;
            isBC = false;
        }
        if (isBC) {
            int compareNegative = bc.compareTo(BigDecimal.ZERO);
            if (compareNegative != 1) {
                errPrice = errPrice.equals("") ? "Must be more than 0" : errPrice;
            }
            int compareMax = bc.compareTo(new BigDecimal("10000"));
            if (compareMax == 0 || compareMax == 1) {
                errPrice = errPrice.equals("") ? "Price can't be no more than 10000 $" : errPrice;
            }
            
        }

        //check anime
        if(anime.length() > 100){
            errAnime = errAnime.equals("") ? "Can input no more than 100 characters" : errAnime;
        }
        if(animeFacade.findAnimeByName(anime) == null){
            errAnime = errAnime.equals("") ? "This anime is not exist" : errAnime;
        }
        //check description
        if(description.length()<=10 || description.length() > 500){
            errDescription = errDescription.equals("") ? "Need at least 10 characters and no more than 500 characters" : errDescription;
        }
        //check image
        if(image1.equals("")){
            errImage = errImage.equals("") ? "Require at least 1 image" : errImage;
        }
        //errors
        JSONObject result = new JSONObject();
        result.put("errAnime", errAnime);
        result.put("errDescription", errDescription);
        result.put("errImage", errImage);
        result.put("errName", errName);
        result.put("errPrice", errPrice);
        result.put("errQuantity", errQuantity);
        response.getWriter().print(result.toJSONString());
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
        HttpSession session = request.getSession();
        if (session.getAttribute("role") == null) {
            response.sendRedirect(request.getContextPath() + "/User/login");
            return;
        }
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/rating":
                ratingProduct(request, response);
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
        String name = request.getParameter("name").trim();
        int quantity = Integer.parseInt(request.getParameter("quantity").trim());
        BigDecimal price = new BigDecimal(request.getParameter("price").trim());
        String description = request.getParameter("description").trim();
        //User
        int userId = Integer.parseInt(request.getParameter("userId"));
        Models.Users user = usersFacade.find(userId);
        //Anime
        String animeName = request.getParameter("anime").trim();
        Models.Anime anime = animeFacade.findAnimeByName(animeName);
        //Category
        int categoryId = Integer.parseInt(request.getParameter("category").trim());
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
            newProduct = uploadProductMedia(request, response, productFacade.find(newProduct.getId()));
            productFacade.edit(newProduct);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during insert product", e.getMessage());
        }
        //</editor-fold>
    }

    protected Product uploadProductMedia(HttpServletRequest request, HttpServletResponse response, Product productId)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action upload images">
        productId.getMediaCollection().clear();
        String image1 = request.getParameter("image1").trim();
        String image2 = request.getParameter("image2").trim();
        String image3 = request.getParameter("image3").trim();
        String image4 = request.getParameter("image4").trim();
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
                    productId.getMediaCollection().add(media);

                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Problems during saving image name", e.getMessage());
                }

            }
        }
        return productId;
        //</editor-fold>
    }

    protected void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action edit product">
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product editedProduct = editProductMedia(request, response, productFacade.find(productId));

        String name = request.getParameter("name").trim();
        int quantity = Integer.parseInt(request.getParameter("quantity").trim());
        BigDecimal price = new BigDecimal(request.getParameter("price").trim());
        String description = request.getParameter("description").trim();
        //Anime
        String animeName = request.getParameter("anime").trim();
        Models.Anime anime = animeFacade.findAnimeByName(animeName);
        //Category
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Models.Category category = categoryFacade.find(categoryId);

        editedProduct.setName(name);
        editedProduct.setDescription(description);
        editedProduct.setPrice(price);
        editedProduct.setQuantity(quantity);
        editedProduct.setAnimeId(anime);
        editedProduct.setCategoryId(category);
        editedProduct.setStatus(Short.parseShort("0"));
        editedProduct.setUpdateAt(new Date());
        editedProduct.setAlertNote("");
        try {
            productFacade.edit(editedProduct);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during edit product", e.getMessage());
        }
        //</editor-fold>
    }

    protected Product editProductMedia(HttpServletRequest request, HttpServletResponse response, Product productId)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action edit product image">
        for (Media media : productId.getMediaCollection()) {
            mediaFacade.remove(media);
        }
        Product product = uploadProductMedia(request, response, productId);
        return product;
        //</editor-fold>
    }

    protected void repairProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action repair product image">
        editProduct(request, response);
        //</editor-fold>  
    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    
    protected void ratingProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        //<editor-fold defaultstate="collapsed" desc="action rating product">
        double rating = Double.parseDouble(request.getParameter("rating"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productFacade.find(productId);
        //new product rating
        ProductRating rate = new ProductRating();
        rate.setId(0);
        rate.setProductId(product);
        rate.setRating(rating);
        rate.setUsersId(usersFacade.find(request.getSession().getAttribute("userid")));
        try {
            productRatingFacade.create(rate);
            Product addRatingProduct = addRatingToProduct(product, rate);
            productFacade.edit(addRatingProduct);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during rating product", e.getMessage());
        }
        //</editor-fold> 
    }
    
    protected Product addRatingToProduct(Product product, ProductRating rate){
        ProductRating pr = productRatingFacade.find(rate.getId());
        product.getProductRatingCollection().add(pr);
        return product;
    }

}
