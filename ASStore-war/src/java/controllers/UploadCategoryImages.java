/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.CategoryFacadeLocal;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Tien Phat
 */
@WebServlet(name = "uploadImagesCreate", urlPatterns = {"/UploadCategoryImages"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadCategoryImages extends HttpServlet {

    @EJB
    private CategoryFacadeLocal categoryFacade;

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

        String image = getImageName(request, response);
        String name = request.getParameter("name");
        String errorMess = "";
        Models.Category getcate = (Models.Category) categoryFacade.getCateByName(name);
        Models.Category category = new Models.Category();
        category.setId(0);
        category.setEnabled(true);
        boolean error = false;
        if (name.trim().equals("")) {
            errorMess = errorMess.equals("") ? "Name Category can't be blank" : errorMess;
            error = true;
        } else {
            if (getcate != null) {
                errorMess = errorMess.equals("") ? "Name Category exist" : errorMess;
                error = true;
            } else {
                request.setAttribute("name", name);
                category.setName(name);
            }
        }
        
        request.setAttribute("image", image);
        category.setPicture(image);

        if (error) {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/admin/category-create.jsp").forward(request, response);
        }

        try {
            categoryFacade.create(category);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        response.sendRedirect(request.getContextPath() + "/admin/category/list");
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
        String imageResourcePath = File.separator + "web" + File.separator + "assets" + File.separator + "img" + File.separator + "categories" + File.separator;
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

}
