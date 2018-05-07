<%-- 
    Document   : cart
    Created on : Apr 29, 2018, 12:20:17 PM
    Author     : zerox
--%>

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
              <th class="column-5 text-center">Total</th>
            </tr>
            <jsp:include page="components/cart/cartItem.jsp">
              <jsp:param name="image" value="kakashi_cup.jpg" />
              <jsp:param name="name" value="Kakashi Hatake coffee cup" />
              <jsp:param name="price" value="22" />
              <jsp:param name="quantity" value="2" />
              <jsp:param name="total" value="44" />
            </jsp:include>
          </table>
        </div>
      </div>

      <!-- Total -->
      <div class="bo9 w-size18 p-l-40 p-r-40 p-t-30 p-b-38 m-t-30 m-r-0 m-l-auto p-lr-15-sm">
        <h5 class="m-text20 p-b-24">
          Cart Checkout
        </h5>

        <!--  -->
        <div class="flex-w" style="width: 100%">
          <label>Delivery address</label>
          <div class="bo4 of-hidden m-b-20" style="width: 100%">
            <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="leave blank if you already signed up"></textarea>
          </div>
        </div>

        <!--  -->
        <div class="flex-w flex-sb-m p-t-26 p-b-30">
          <span class="m-text22 w-size19 w-full-sm">
            Total:
          </span>

          <span class="m-text21 w-size20 w-full-sm">
            $39.00
          </span>
        </div>

        <div class="size15 trans-0-4">
          <!-- Button -->
          <button class="flex-c-m sizefull bg1 bo-rad-23 hov1 s-text1 trans-0-4">
            Proceed to Checkout
          </button>
        </div>
      </div>
    </div>
  </section>

</t:layout>