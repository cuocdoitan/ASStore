/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.AnimeFacadeLocal;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Tien Phat
 */
@WebServlet(name = "Anime_Edit", urlPatterns = {"/Anime_Edit"})
@MultipartConfig
public class Anime_Edit extends HttpServlet {

    @EJB
    private AnimeFacadeLocal animeFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        // Create path components to save the file
        final String path = getServletContext().getRealPath("assets/img/anime/");
        final Part filePart = request.getPart("image");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        //edit anime
        String pic = fileName;
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String id = request.getParameter("anime");
        Models.Anime anime = animeFacade.find(Integer.parseInt(id));
        String errorMess = "";
        
        try {
            boolean error = false;
            if (name.trim().equals("")) {
                errorMess = errorMess.equals("") ? "Anime name can't be blank" : errorMess;
                error = true;
            }
            Models.Anime foundAnime = animeFacade.findAnimeByName(name);
            if(foundAnime != null){
                if(!foundAnime.getName().equalsIgnoreCase(anime.getName())){
                    errorMess = errorMess.equals("") ? " This anime already existed" : errorMess;
                error = true;
                }
            }
            if (description.trim().equals("")) {
                errorMess = errorMess.equals("") ? " Description can't be blank" : errorMess;
                error = true;
            }
            if(pic != null){
                anime.setPicture(pic);
            }
            if (error) {
                request.setAttribute("animes", anime);
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/admin/anime-list-update.jsp").forward(request, response);
                return;
            }
            anime.setName(name);
            anime.setDescription(description);
            anime.setPicture(pic);
            animeFacade.edit(anime);

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
        response.sendRedirect(request.getContextPath() + "/admin/anime/list");
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                    String rFileName = "n_" + (new Date()).getTime() + "." + extension;
                    return rFileName;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

}
