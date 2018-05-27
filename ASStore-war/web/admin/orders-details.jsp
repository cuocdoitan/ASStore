<%-- 
    Document   : orders-details
    Created on : May 4, 2018, 9:21:14 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminLayout>
  <section class="cart bgwhite p-t-70 p-b-100">
    <div class="container">
      <h1 class="text-center m-b-50">Order #${orderId} details</h1>
      <!-- Cart item -->
      <div class="container-table-cart pos-relative">
        <div class="wrap-table-shopping-cart bgwhite">
          <table class="table-shopping-cart">
            <tr class="table-head">
              <th class="column-1">Image</th>
              <th class="column-2 text-center">Product</th>
              <th class="column-3 text-center">Price</th>
              <th class="column-4 text-center">Quantity</th>
              <th class="column-5 text-center">Total</th>
            </tr>

            <c:forEach items="${details}" var="detail">
              <jsp:include page="/user/components/confirmOrder/cartItem.jsp">
                <jsp:param name="image" value="${images.get(detail.productId.id)}" />
                <jsp:param name="name" value="${detail.productId.name}" />
                <jsp:param name="price" value="${detail.unitPrice}" />
                <jsp:param name="quantity" value="${detail.quantity}" />
                <jsp:param name="total" value="${detail.unitPrice * detail.quantity}" />
              </jsp:include>
            </c:forEach>

            <tr class="table-row">
              <td class="column-1"></td>
              <td class="column-2"></td>
              <td class="column-3"></td>
              <td class="column-4" style="text-align: right">Order total:</td>
              <td class="column-5" style="text-align: left">$${orderTotal}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </section>

</t:adminLayout>