/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Models.Product;
import SB.CategoryFacadeLocal;
import SB.MediaFacadeLocal;
import SB.ProductFacadeLocal;
import SB.UsersFacadeLocal;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
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
    private UsersFacadeLocal usersFacade;

    @EJB
    private CategoryFacadeLocal categoryFacade;

    @EJB
    private MediaFacadeLocal mediaFacade;

    @EJB
    private ProductFacadeLocal productFacade;
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

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
        String adminRequest = request.getPathInfo();
        switch (adminRequest) {
            case "/list":
                listPage(request, response);
                break;
            case "/approving-list":
                approvingListPage(request, response);
                break;
            case "/deny":
                denyPage(request, response);
                break;
            case "/details":
                detailsPage(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void listPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="show list products">
        String search = request.getParameter("search");
        if (search == null) {
            request.setAttribute("listProduct", productFacade.getListManageProduct());
            request.getRequestDispatcher("/admin/products-list.jsp").forward(request, response);
        } else {
            String searchBy = request.getParameter("SearchBy");
            if (searchBy.equals("productName")) {
                request.setAttribute("listProduct", productFacade.searchProductByName(search));
                request.getRequestDispatcher("/admin/components/productList/list.jsp").forward(request, response);
            }
            if (searchBy.equals("phoneNumber")) {
                request.setAttribute("listProduct", productFacade.searchProductByUserPhoneNumber(search));
                request.getRequestDispatcher("/admin/components/productList/list.jsp").forward(request, response);
            }

        }
        //</editor-fold> 
    }

    protected void approvingListPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="show list pending products">
        request.setAttribute("listApprovingProduct", productFacade.getListApprovingProduct());
        request.getRequestDispatcher("/admin/products-approving-list.jsp").forward(request, response);
        //</editor-fold> 
    }

    protected void denyPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to deny product page">
        int productId_deny = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("product", productFacade.find(productId_deny));
        request.setAttribute("categories", categoryFacade.findAll());
        request.getRequestDispatcher("/admin/products-deny.jsp").forward(request, response);
        //</editor-fold> 
    }

    protected void detailsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="go to details product page">
        int productId_detail = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("product", productFacade.find(productId_detail));
        request.getRequestDispatcher("/admin/products-details.jsp").forward(request, response);
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
        String adminRequest = request.getPathInfo();
        switch (adminRequest) {
            case "/approve":
                approveProduct(request, response);
                break;
            case "/deny":
                denyProduct(request, response);
                break;
            case "/delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }

    }

    protected void approveProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action approve product">
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productFacade.find(productId);
        product.setStatus(Short.parseShort("1"));
        try {
            productFacade.edit(product);
            Models.Users user = product.getUsersId();
            user.setNumberOfNotification(user.getNumberOfNotification() + 1);
            usersFacade.edit(user);
            request.setAttribute("listApprovingProduct", productFacade.getListApprovingProduct());
            request.getRequestDispatcher("/admin/components/productApprovingList/list.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during denying product", e.getMessage());
        }
        //</editor-fold> 
    }

    protected void denyProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="action deny product">
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productFacade.find(productId);
        product.setAlertNote(request.getParameter("alertNote").trim());
        product.setStatus(Short.parseShort("2"));
        try {
            productFacade.edit(product);
            Models.Users user = product.getUsersId();
            user.setNumberOfNotification(user.getNumberOfNotification() + 1);
            usersFacade.edit(user);
            response.sendRedirect(request.getContextPath() + "/admin/products/approving-list");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during denying product", e.getMessage());
        }
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
            response.sendRedirect(request.getContextPath() + "/admin/products/list");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problems during deleting product", e.getMessage());
        }
        //</editor-fold> 
    }

}
