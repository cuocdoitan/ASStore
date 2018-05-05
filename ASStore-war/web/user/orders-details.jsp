<%-- 
    Document   : orders-details
    Created on : May 4, 2018, 9:21:14 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <section class="cart bgwhite p-t-70 p-b-100">
    <div class="container">
      <h1 class="text-center m-b-50">Order #1 details</h1>
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
            <jsp:include page="components/cart/cartItem.jsp">
              <jsp:param name="image" value="kakashi_cup.jpg" />
              <jsp:param name="name" value="Kakashi Hatake coffee cup" />
              <jsp:param name="price" value="22" />
              <jsp:param name="quantity" value="2" />
              <jsp:param name="total" value="44" />
            </jsp:include>
            <tr class="table-row">
              <td class="column-1"></td>
              <td class="column-2"></td>
              <td class="column-3"></td>
              <td class="column-4" style="text-align: right">Order total:</td>
              <td class="column-5" style="text-align: left">$22</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </section>

</t:layout>