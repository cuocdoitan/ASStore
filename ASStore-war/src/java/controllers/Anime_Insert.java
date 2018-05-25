/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.AnimeFacadeLocal;
import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
 * @author zerox
 */
@WebServlet(name = "Anime_Insert", urlPatterns = {"/Anime_Insert"})
@MultipartConfig
public class Anime_Insert extends HttpServlet {

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
        // Create path components to save the file
        final String path = getServletContext().getRealPath("assets/img/anime/");
        final Part filePart = request.getPart("pic");
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
        //post anime
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String pic = fileName;
        String errorMess = "";
//                if (name.trim().isEmpty()) {
//                    request.setAttribute("error", "name can't be null");
//                    request.getRequestDispatcher("/admin/anime-addnew.jsp").forward(request, response);
//                }
        try {
            Models.Anime anime = new Models.Anime();
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
            if (pic.equals("")) {
                errorMess = errorMess.equals("") ? " Picture can't be blank" : errorMess;
                error = true;
            } else {
                request.setAttribute("pic", pic);
                anime.setPicture(pic);
            }
            if (error) {
                request.setAttribute("error", errorMess);
                request.getRequestDispatcher("/admin/anime-addnew.jsp").forward(request, response);
                return;
            }

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
