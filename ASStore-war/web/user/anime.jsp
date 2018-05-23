<%-- 
    Document   : anime
    Created on : May 1, 2018, 1:39:14 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
  <div class="container">
    <div class="row">
      <div class="col-12 m-t-30">
        <h1 class="m-b-30 text-center">All Anime</h1>
        <table border="1" style="width: 100%" class="anime-list">
          <thead>
            <tr>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">ID</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Description</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Picture</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${animeList}" var="anime" >
              <tr>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  ${anime.id}
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  ${anime.name}
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  <div class="anime-description less">
                    ${anime.description}
                  </div>
                  <p class="expand" onclick="expandAnime(this)">Read more</p>
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  <img src="<c:url value="/assets/img/anime/${anime.picture}" />" height="200px" alt="">
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                    <a href="<c:url value=''/>" style="color: #3498db">related products</a>
                  <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                </td> 
              </tr>
            </c:forEach>
          </tbody> 
        </table>
      </div>
    </div>
  </div>
</t:layout>