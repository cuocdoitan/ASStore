/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import SB.AnimeFacadeLocal;
import Models.Anime;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author zerox
 */
@WebServlet(name = "animeApi", urlPatterns = {"/animeApi"})
public class animeApi extends HttpServlet {

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
    response.setContentType("application/json");
      System.out.println("Someone calling me");
      System.out.println("getContextPath" + request.getContextPath());
    PrintWriter out = response.getWriter();
    List<Anime> animes = animeFacade.findAll();

    JSONArray animesJSON = new JSONArray();

    if (request.getParameterMap().containsKey("name")) {
      final String name = request.getParameter("name").trim();
      Collections.sort(animes, new Comparator<Anime>() {
        @Override
        public int compare(Anime o1, Anime o2) {
          return -Integer.compare(getSimilarity(o1, name), getSimilarity(o2, name));
        }

        private int getSimilarity(Anime anime, String keyword) {
          return anime.getName().toLowerCase().length() - anime.getName().toLowerCase().replace(keyword, "").length();
        }
      });
      
      for (Iterator<Anime> iterator = animes.iterator(); iterator.hasNext();) {
        Anime anime = iterator.next();
        int similarity = anime.getName().toLowerCase().length() - anime.getName().toLowerCase().replace(name, "").length();
        
        if (similarity == 0) {
          iterator.remove();
        }
      }
    }
    
    for (Anime anime : animes) {
      JSONObject animeJSON = new JSONObject();
      animeJSON.put("id", anime.getId());
      animeJSON.put("name", anime.getName());
      animeJSON.put("picture", anime.getPicture());
      animesJSON.add(animeJSON);
    }
    out.print(animesJSON);
    out.flush();
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
