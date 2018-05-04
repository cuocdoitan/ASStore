/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author zerox
 */
@WebServlet(name = "products", urlPatterns = {"/products/*"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class Products extends HttpServlet {
    


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
                request.getRequestDispatcher("/user/products-list.jsp").forward(request, response);
                break;
            case "/new-product":
                request.getRequestDispatcher("/user/products-insert.jsp").forward(request, response);
                break;
            case "/details":
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
                uploadImages(request, response);
                break;
            case "/repair-product":
                
                break;
            case "/edit":
                
                break;
            case "/delete":
                
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    
  }

  protected void uploadImages(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      String imageResourceAbsolutePath = getImageResourceAbsolutePath(request);
        for(Part part : request.getParts()){
            String imageName = extractFileName(part);
            imageName = new File(imageName).getName();
            if(!imageName.equals("")){
                try {
                    InputStream inputStream = part.getInputStream();
                    String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + imageName;
                    String imageAbsolutePath = imageResourceAbsolutePath + fileName;
                    File fileSaveDir = new File(imageAbsolutePath);
                    
                    BufferedImage e = ImageIO.read(inputStream);
                    ImageIO.write(e, "png", fileSaveDir);
                } catch (IOException e) {
                    
                }
                
            }
        }
  }
  
  
    private String getImageResourceAbsolutePath(HttpServletRequest request){
        String appPath = request.getServletContext().getRealPath("");
        String dist = "dist"+File.separator+"gfdeploy";
        int distPosition = appPath.indexOf(dist);
        String projectPath = appPath.substring(0, distPosition - 1);
        String contextPath = request.getContextPath();
        String imageResourcePath = File.separator + "web" + File.separator + "assets" + File.separator + "img" + File.separator + "products" + File.separator;
        String imageResourceAbsolutePath = projectPath + contextPath + imageResourcePath;
        return imageResourceAbsolutePath;
    }
  
    private String extractFileName(Part part) {
    String contentDisp = part.getHeader("content-disposition");
    String[] items = contentDisp.split(";");
        System.out.println("number of items : "+items.length);
    for (String s : items) {
        System.out.println("extracted string : "+s);
        
        if (s.trim().startsWith("filename")) {
            return s.substring(s.indexOf("=") + 2, s.length()-1);
        }
    }
    return "";
  }
  
  
  
}
