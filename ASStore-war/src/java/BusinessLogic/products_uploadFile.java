/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import static com.sun.xml.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author zerox
 */
@WebServlet(name = "products_uploadFile", urlPatterns = {"/products_uploadFile"})
@MultipartConfig
public class products_uploadFile extends HttpServlet {

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
    // Create path components to save the file
    final String path = getServletContext().getRealPath("assets/img/products/");
    final Part filePart = request.getPart("file");
    final String fileName = getFileName(filePart);

    OutputStream out = null;
    InputStream filecontent = null;
    final PrintWriter writer = response.getWriter();

    try {
      out = new FileOutputStream(new File(path + File.separator
              + fileName));
      filecontent = filePart.getInputStream();

      int read = 0;
      final byte[] bytes = new byte[1024];
      
      while ((read = filecontent.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      writer.println("{\"filename\":\"" + fileName + "\"}");
      
    } catch (FileNotFoundException fne) {
      writer.println("{ status: 'failed', name: '" + fileName + "', error: '" + fne.getMessage() + "', path: '" + path + "' }");

      LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
              new Object[]{fne.getMessage()});
    } finally {
      if (out != null) {
        out.close();
      }
      if (filecontent != null) {
        filecontent.close();
      }
      if (writer != null) {
        writer.close();
      }
    }
  }

  private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    for (String content : part.getHeader("content-disposition").split(";")) {
      if (content.trim().startsWith("filename")) {
        String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
          //String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
          String rFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + fileName ;
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
