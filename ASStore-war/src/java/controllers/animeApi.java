/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
    PrintWriter out = response.getWriter();
    ArrayList<Anime> animes = new ArrayList<>();
    animes.add(new Anime(1, "Naruto", "17450.jpg"));
    animes.add(new Anime(2, "Trigun", "20310.jpg"));
    animes.add(new Anime(3, "One Piece", "73245.jpg"));
    animes.add(new Anime(4, "Hunter x Hunter", "19473.jpg"));

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
      animeJSON.put("id", anime.id);
      animeJSON.put("name", anime.name);
      animeJSON.put("picture", anime.picture);
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
