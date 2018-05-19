<%-- 
    Document   : orders-checkcode
    Created on : May 5, 2018, 9:56:14 AM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pureLayout>
  <div class="container">
    <div class="row">
      <div class="col-12 m-t-20">
        <h1 class="m-b-20">Confirm order</h1>
        <p style="color: red" class="m-b-30">${error}</p>
        <p style="color: blueviolet" class="m-b-30">${message}</p>
        <form action="check" method="post">
          <label>Order ID</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" value="${orderId}" type="number" name="order" placeholder="Order ID">
          </div>
          <label>Passcode</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" value="${orderPass}" type="password" name="pass" placeholder="Passcode">
          </div>
          <button type="submit" style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
            View details
          </button>
          <button type="submit" name="validate" value="true" style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
            Confirm
          </button>
        </form>
        <c:if test="${details != null}">
          <div class="container-table-cart pos-relative m-b-30">
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
                  <jsp:include page="components/confirmOrder/cartItem.jsp">
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
        </c:if>
      </div>
    </div>
  </div>
</t:pureLayout>