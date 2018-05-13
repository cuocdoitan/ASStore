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
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author zerox
 */
@WebServlet(name = "Anime_admin", urlPatterns = {"/anime/*"})
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
                request.setAttribute("anime", animee);
                request.getRequestDispatcher("/admin/anime-list-update.jsp").forward(request, response);
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String uploadIMG(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageResourceAbsolutePath = getImageResourceAbsolutePath(request);
        for (Part part : request.getParts()) {
            String imageName = extractFileName(part);
            imageName = new File(imageName).getName();
            if (!imageName.equals("")) {
                try {
                    InputStream inputStream = part.getInputStream();
                    String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + imageName;
                    String imageAbsolutePath = imageResourceAbsolutePath + fileName;
                    File fileSaveDir = new File(imageAbsolutePath);

                    BufferedImage e = ImageIO.read(inputStream);
                    ImageIO.write(e, "png", fileSaveDir);
                    inputStream.close();
                    return fileName;
                } catch (IOException fne) {
                    LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                            new Object[]{fne.getMessage()});
                }

            }
        }
        return "";
    }

    protected String getImageName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String imageResourceAbsolutePath = getImageResourceAbsolutePath(request);
        for (Part part : request.getParts()) {
            String imageName = extractFileName(part);
            imageName = new File(imageName).getName();
            if (!imageName.equals("")) {
                try {
                    InputStream inputStream = part.getInputStream();
                    String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + imageName;
                    String imageAbsolutePath = imageResourceAbsolutePath + fileName;
                    File fileSaveDir = new File(imageAbsolutePath);

                    BufferedImage e = ImageIO.read(inputStream);
                    ImageIO.write(e, "png", fileSaveDir);
                    inputStream.close();
                    return fileName;
                } catch (IOException fne) {
                    LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                            new Object[]{fne.getMessage()});
                }

            }
        }
        return "";
    }

    private String getImageResourceAbsolutePath(HttpServletRequest request) {
        String appPath = request.getServletContext().getRealPath("");
        String dist = "dist" + File.separator + "gfdeploy";
        int distPosition = appPath.indexOf(dist);
        String projectPath = appPath.substring(0, distPosition - 1);
        String contextPath = request.getContextPath();
        String imageResourcePath = File.separator + "web" + File.separator + "assets" + File.separator + "img" + File.separator + "anime" + File.separator;
        String imageResourceAbsolutePath = projectPath + contextPath + imageResourcePath;
        return imageResourceAbsolutePath;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        System.out.println("number of items : " + items.length);
        for (String s : items) {
            System.out.println("extracted string : " + s);

            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientRequest = request.getPathInfo();
        switch (clientRequest) {
            case "/create":
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String pic = uploadIMG(request, response);
                String errorMess = "";
//                if (name.trim().isEmpty()) {
//                    request.setAttribute("error", "name can't be null");
//                    request.getRequestDispatcher("/admin/anime-addnew.jsp").forward(request, response);
//                }
                try {
                    Models.Anime anime = new Anime();
                    anime.setId(0);
                    boolean error = false;
                    if (name.trim().equals("")) {
                        errorMess = errorMess.equals("") ? "Anime name can't be blank" : errorMess;
                        error = true;
                    } else {
                        request.setAttribute("name", name);
                        anime.setName(name);
                    }
                    if (description.trim().equals("")) {
                        errorMess = errorMess.equals("") ? " Description can't be blank" : errorMess;
                        error = true;
                    } else {
                        request.setAttribute("description", description);
                        anime.setDescription(description);
                    }
                    if (error) {
                        request.setAttribute("error", errorMess);
                        request.getRequestDispatcher("/admin/anime-addnew.jsp").forward(request, response);
                    }
                    anime.setPicture(pic);
                    anime.setEnabled(true);
                    animeFacade.create(anime);

                } catch (EJBException e) {
                    @SuppressWarnings("ThrowableResultIgnored")
                    Exception cause = e.getCausedByException();
                    if (cause instanceof ConstraintViolationException) {
                        @SuppressWarnings("ThrowableResultIgnored")
                        ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                        for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                            ConstraintViolation<? extends Object> v = it.next();
                            System.err.println(v);
                            System.err.println("==>>" + v.getMessage());
                        }
                    }
                }
                response.sendRedirect("create");
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
