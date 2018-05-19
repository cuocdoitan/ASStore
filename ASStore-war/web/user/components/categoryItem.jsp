<%-- 
    Document   : categoryItem
    Created on : Apr 26, 2018, 12:35:35 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
  <!-- block1 -->
  <div class="block1 hov-img-zoom pos-relative m-b-30">
      <img src="<c:url value='/assets/img/categories/${param.image}'/>" alt="IMG-BENNER" width="200px" height="200px">

    <div class="block1-wrapbtn w-size2">
      <!-- Button -->
      <a href="<c:url value='/category/detailsproduct?category=${param.id}' />" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
        ${param.name}
      </a>
    </div>
  </div>
</div>