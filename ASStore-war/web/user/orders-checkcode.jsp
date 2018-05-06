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
        <label>Order ID</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="pas" placeholder="Order ID">
        </div>
        <label>Passcode</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="pas" placeholder="Passcode">
        </div>
        <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
          View details
        </button>
        <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
          Confirm
        </button>
        <div class="container-table-cart pos-relative m-t-20">
          <div class="wrap-table-shopping-cart bgwhite">
            <table class="table-shopping-cart">
              <tr class="table-head">
                <th class="column-1">Image</th>
                <th class="column-2 text-center">Product</th>
                <th class="column-3 text-center">Price</th>
                <th class="column-4 text-center">Quantity</th>
                <th class="column-5 text-center">Total</th>
              </tr>
              <tr class="table-row">
                <td class="column-1">
                  <div class="cart-img-product b-rad-4 o-f-hidden">
                    <img src="<c:url value="/assets/img/products/kakashi_cup.jpg"/>" alt="IMG-PRODUCT" />
                  </div>
                </td>
                <td class="column-2 text-center">Kakashi cups</td>
                <td class="column-3 text-center">$22</td>
                <td class="column-4 text-center">1</td>
                <td class="column-5 text-center">$22</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</t:pureLayout>