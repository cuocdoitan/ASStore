<%-- 
    Document   : anime-addnew
    Created on : May 6, 2018, 10:35:25 AM
    Author     : leminhtung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminLayout>
  <div class="container">
    <div class="row">
      <div class="col-12 m-r-l-auto m-t-20">
        <h2 class="m-b-30">New Anime</h2>
        <p style="color: red">${error}</p>
        <form method="post" action="<c:url value="/admin/anime/create" />" enctype="multipart/form-data">
          <label>Anime Name</label>
          <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="text" value="${name}" name="name">
          </div>
          <label>Anime Description</label>
          <div class="bo4 of-hidden m-b-20">
            <textarea style="width: 100%; border: none; padding: 20px" rows="10" name="description" placeholder="Product description">${anime.description}</textarea>
          </div>
          <label>Anime Picture</label>
          <div class="m-b-20">
            <input type="file" id="inputPhoto" name="pic" accept="image/*"/>
            <br/>
          </div>
          <div class="m-b-20">
            <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
              Add 
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</t:adminLayout>
