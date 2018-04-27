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

      <jsp:include page="components/index/productItem.jsp">
        <jsp:param name="image" value="fairytail_shoe.jpg"/>
        <jsp:param name="name" value="Fairy Tail Sneaker"/>
        <jsp:param name="price" value="75.20"/>
      </jsp:include>
    </div>
  </section>
</t:layout>