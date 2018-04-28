<%-- 
    Document   : categoryList
    Created on : Apr 28, 2018, 9:37:56 AM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  
  <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(assets/img/category_banner.jpg); background-position-y: 100%">
    <div class="mask">

    </div>
    <h2 class="l-text2 t-center" style="z-index: 10">
      Categories
    </h2>
    <p class="m-text13 t-center" style="z-index: 10">
      Find your favorite products using our wide range of categories
    </p>
  </section>
  
  <div class="banner bgwhite p-t-40 p-b-40">
    <div class="container">
      <div class="row">
        <jsp:include page="components/categoryItem.jsp">
          <jsp:param name="image" value="shoe.jpg"/>
          <jsp:param name="name" value="Shoes"/>
        </jsp:include>
      </div>
    </div>
  </div>
</t:layout>