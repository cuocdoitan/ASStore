<%-- 
    Document   : productImageSlider
    Created on : Apr 26, 2018, 6:26:32 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="w-size13 p-t-30 respon5">
  <div class="wrap-slick3 flex-sb flex-w">
    <div class="wrap-slick3-dots"></div>

    <div class="slick3">
      <c:forEach items="${param.images}" var="image">
      <div class="item-slick3" data-thumb="${image}">
        <div class="wrap-pic-w">
          <img src="${image}" alt="IMG-PRODUCT">
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
</div>