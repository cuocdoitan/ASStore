<%-- 
    Document   : cartItem
    Created on : Apr 29, 2018, 1:05:22 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tr class="table-row">
  <td class="column-1">
    <div class="cart-img-product b-rad-4 o-f-hidden">
      <img src="<c:url value="/assets/img/products/${param.image}"/>" alt="IMG-PRODUCT" />
    </div>
  </td>
  <td class="column-2 text-center">${param.name}</td>
  <td class="column-3 text-center">$${param.price}</td>
  <td class="column-4 text-center">${param.quantity}</td>
  <td class="column-5 text-center">$<span id="ps-${param.id}">${param.total}</span></td>
  <td class="column-6 text-center">
    <c:if test="${param.userId != 'guest'}">
      <input type="hidden" id="coupon-p-${param.id}" value="" />
      <p style="color: #007bff; cursor: pointer" onclick="addCoupon('<c:url value="/cart/addCoupon" />', ${param.id})">Coupon</p>
    </c:if>
    <a href="<c:url value="/cart/remove?id=${param.id}" />" onclick="return confirm('Are you sure?')" style="color: #bd4147">Remove</a>
  </td>
</tr>