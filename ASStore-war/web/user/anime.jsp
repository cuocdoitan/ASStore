<%-- 
    Document   : anime
    Created on : May 1, 2018, 1:39:14 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
  
  <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(assets/img/category_banner.jpg); background-position-y: 100%">
    <div class="mask">

    </div>
    <h2 class="l-text2 t-center" style="z-index: 10">
      Anime
    </h2>
  </section>
  
<!--  <div class="banner bgwhite p-t-40 p-b-40">
    <div class="container">
        <div class="row">
              <c:forEach items="${animeList}" var="anime">
                  <div class="block2">
    <div class="block2-img wrap-pic-w of-hidden pos-relative">
                <img src="<c:url value='/assets/img/anime/${anime.picture}'/>" alt="IMG-PRODUCT">
      <div class="block2-overlay trans-0-4">
        <div class="block2-btn-addcart w-size1 trans-0-4">
           Button 
          <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
            Add to Cart
          </button>
        </div>
      </div>
    </div>

    <div class="block2-txt p-t-20">
      <a href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
        ${anime.name}
      </a>
    </div>
  </div>
              </c:forEach>
          </div>
    </div>
  </div>-->
</t:layout>
