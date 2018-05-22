<%-- 
    Document   : coupon-create
    Created on : May 20, 2018, 9:40:16 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <h1 class="m-t-30 m-b-20 text-center">Create coupon</h1>
  <section class="bgwhite p-t-70 p-b-50">
    <div class="container">
      <p style="color:red">${error}</p>
      <p style="color: #0062cc">${mess}</p>
      <form action="<c:url value="/coupon/create"/>"  method="post">
        <label>Product</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" value="${product}" placeholder="product" type="text" name="product" id="coupon_product" required/>
        </div>
        <input type="hidden" name="productId" value="${productId}" />
        <label>Coupon code</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" value="${coupon}" type="text" maxlength="20" name="coupon" placeholder="Coupon" required/>
        </div>
        <label>Expire date</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" value="${expire}" type="date" name="expire" required/>
        </div>
        <label>Discount percentage (from 10 to 90%)</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" value="${percentage}" type="number" min="10" max="90" name="percentage" placeholder="Discount percentage" required/>
        </div>
        <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
          Create coupon
        </button>
      </form>
    </div>
      <input type="hidden" value="<c:url value="/productApi"/>" id="couponProductApi" />
      <input type="hidden" value="<c:url value="/assets/img/products/"/>" id="productImageURL" />
  </section>
</t:layout>
