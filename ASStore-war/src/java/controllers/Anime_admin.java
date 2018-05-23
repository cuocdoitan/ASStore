/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.AnimeFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import Models.Anime;
import Models.Roles;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import javax.ejb.EJBException;
import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author zerox
 */
@WebServlet(name = "Anime_admin", urlPatterns = {"/admin/anime/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class Anime_admin extends HttpServlet {

    @EJB
    private AnimeFacadeLocal animeFacade;

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
        HttpSession sess = request.getSession();
        
        switch (clientRequest) {
            case "/list":
                List<Anime> listAnime = animeFacade.findAll();
                request.setAttribute("animeList", listAnime);
                request.getRequestDispatcher("/admin/anime-list.jsp").forward(request, response);
                break;
            case "/create":
                request.getRequestDispatcher("/admin/anime-addnew.jsp").forward(request, response);
                break;
            case "/update":
                Models.Anime animee = animeFacade.find(Integer.parseInt(request.getParameter("anime")));
                request.setAttribute("animes", animee);
                request.getRequestDispatcher("/admin/anime-list-update.jsp").forward(request, response);
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
            case "/create":
                request.getRequestDispatcher("/Anime_Insert").forward(request, response);
                break;
            case "/update":
                request.getRequestDispatcher("/Anime_Edit").forward(request, response);
                break;
            case "/delete":
                Models.Anime anime = animeFacade.find(Integer.parseInt(request.getParameter("anime")));
                
                animeFacade.remove(anime);
                response.sendRedirect("list");
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
