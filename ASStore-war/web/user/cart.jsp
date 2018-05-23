<%-- 
    Document   : cart
    Created on : Apr 29, 2018, 12:20:17 PM
    Author     : zerox
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <section class="cart bgwhite p-t-70 p-b-100">
    <div class="container">
      <h1 class="text-center m-b-50">Your cart</h1>
      <!-- Cart item -->
      <div class="container-table-cart pos-relative">
        <div class="wrap-table-shopping-cart bgwhite">
          <table class="table-shopping-cart">
            <tr class="table-head">
              <th class="column-1">Image</th>
              <th class="column-2 text-center">Product</th>
              <th class="column-3 text-center">Price</th>
              <th class="column-4 text-center">Quantity</th>
              <th class="column-5 text-center">Discount</th>
              <th class="column-5 text-center">Total</th>
              <th class="column-6 text-center p-l-20 p-r-20">Action</th>
            </tr>
            <c:forEach items="${details}" var="detail">
              <jsp:include page="/user/components/cart/cartItem.jsp">
                <jsp:param name="id" value="${detail.id}" />
                <jsp:param name="coupon" value="${detail.coupon}" />
                <jsp:param name="image" value="${images.get(detail.productId.id)}" />
                <jsp:param name="name" value="${detail.productId.name}" />
                <jsp:param name="price" value="${detail.unitPrice}" />
                <jsp:param name="discount" value="${coupons.get(detail.coupon)}" />
                <jsp:param name="quantity" value="${detail.quantity}" />
                <jsp:param name="total" value="${stotals.get(detail.id)}" />
                <jsp:param name="userId" value="${userId}" />
              </jsp:include>
            </c:forEach>
          </table>
        </div>
      </div>

      <c:if test="${cartTotal > 0}">
      <!-- Total -->
      <div class="bo9 w-size18 p-l-40 p-r-40 p-t-30 p-b-38 m-t-30 m-r-0 m-l-auto p-lr-15-sm">
        <h5 class="m-text20 p-b-24">
          Cart Checkout
        </h5>
        <p style="color: red">${error}</p>
        <form method="post" action="<c:url value="/cart/checkout" />">
          <!--  -->
          <div class="flex-w" style="width: 100%">
            <label>Contact phone</label>
            <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="text" id="phone" placeholder="Contact phone.." name="phone" value="${phone}" />
            </div>
            <label>Delivery address</label>
            <div class="bo4 of-hidden m-b-20" style="width: 100%">
              <textarea style="width: 100%; border: none; padding: 20px" rows="3" name="address" placeholder="Delivery address">${address}</textarea>
            </div>
            <c:choose>
              <c:when test="${isCard != true}">
                <div class="card-details" style="height: 0; overflow: hidden; width: 100%">
                </c:when>
                <c:otherwise>
                  <div class="card-details" style="height: auto; overflow: hidden; width: 100%">
                  </c:otherwise>
                </c:choose>
                    <h4 class="m-b-20">Card details (Visa)</h4>
                <label>Card number</label>
                <div class="bo4 of-hidden size15 m-b-20">
                  <input class="sizefull s-text7 p-l-22 p-r-22" type="text" id="cardNumber" name="cardNumber" value="${cardNumber}">
                </div>
                <label>Security code</label>
                <div class="bo4 of-hidden size15 m-b-20">
                  <input class="sizefull s-text7 p-l-22 p-r-22" type="number" id="cardSecurity" name="cardSecurity" value="${cardSecurity}">
                </div>
                <label>Expiry date</label>
                <div class="bo4 of-hidden size15 m-b-20">
                  <input class="sizefull s-text7 p-l-22 p-r-22" type="date" value="${expiryDate}" name="expiryDate">
                </div>
              </div>
              <p class="showCreditCard" onclick="showCreditCard(this)">Pay by credit card</p>
            </div>

            <!--  -->
            <div class="flex-w flex-sb-m p-t-26 p-b-30">
              <span class="m-text22 w-size19 w-full-sm">
                Total:
              </span>

              <span class="m-text21 w-size20 w-full-sm">
                $<span id="ctotal">${cartTotal}</span>
              </span>
            </div>
            <c:choose>
              <c:when test="${isCard == true}">
                <input type="hidden" name="method" value="card" />
              </c:when>
              <c:otherwise>
                <input type="hidden" name="method" value="cash" />
              </c:otherwise>
            </c:choose>
            <div class="size15 trans-0-4">
              <!-- Button -->
              <button type="submit" class="flex-c-m sizefull bg1 bo-rad-23 hov1 s-text1 trans-0-4">
                Proceed to Checkout
              </button>
            </div>
        </form>
      </div>
    </div>
      </c:if>
  </section>
  <input type="hidden" name="getPriceURL" value="<c:url value="/cart/getPrice" />" />
  <input type="hidden" name="getTotalURL" value="<c:url value="/cart/getTotal" />" />
</t:layout>