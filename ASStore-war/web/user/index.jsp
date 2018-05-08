<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(assets/img/index_banner.jpg); background-position: center 300%">
    <div class="mask">
    </div>
    <h2 class="l-text2 t-center" style="z-index: 10">
      AS Store
    </h2>
    <p class="m-text13 t-center" style="z-index: 10">
      Where anime products come to life
    </p>
  </section>
  <div class="banner bgwhite p-t-40 p-b-40">
    <div class="container">
      <div class="row">
        <jsp:include page="components/categoryItem.jsp">
          <jsp:param name="image" value="shoe.jpg"/>
          <jsp:param name="name" value="Shoes"/>
        </jsp:include>
        <jsp:include page="components/categoryItem.jsp">
          <jsp:param name="image" value="bag.jpg"/>
          <jsp:param name="name" value="Bags"/>
        </jsp:include>
        <jsp:include page="components/categoryItem.jsp">
          <jsp:param name="image" value="cups.jpg"/>
          <jsp:param name="name" value="Cups"/>
        </jsp:include>
      </div>
    </div>
  </div>
  <section class="bgwhite p-t-45 p-b-58">
    <div class="container">
      <div class="sec-title p-b-22">
        <h3 class="m-text5 t-center">
          Newest Products
        </h3>
      </div>
        <div class="row">
            <c:forEach items="${listProduct}" var="product">
                <jsp:include page="components/index/productItem.jsp">
                    <jsp:param name="productId" value="${product.id}"/>
                    <jsp:param name="name" value="${product.name}"/>
                    <jsp:param name="price" value="${product.price}"/>
                </jsp:include>
            </c:forEach>
        </div>
        <a href="<c:url value="/products/list"/>">
            <span style="font-weight: bold">SEE ALL &gt; &gt;</span>
        </a>
      
    </div>
      <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
              <input type="text" name="anime_name" id="anime_name" class="s-text7 size6 p-l-23 p-r-50" placeholder="Search products by anime..." />
              <button class="flex-c-m size5 ab-r-m color2 color0-hov trans-0-4">
                <i class="fs-12 fa fa-search" aria-hidden="true"></i>
              </button>
       </div>
      <div id="test_ajax_result"></div>
  </section>
</t:layout>