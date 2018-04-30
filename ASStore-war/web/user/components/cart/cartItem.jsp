<%-- 
    Document   : cartItem
    Created on : Apr 29, 2018, 1:05:22 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tr class="table-row">
  <td class="column-1">
    <div class="cart-img-product b-rad-4 o-f-hidden">
      <img src="assets/img/products/${param.image}" alt="IMG-PRODUCT">
    </div>
  </td>
  <td class="column-2 text-center">${param.name}</td>
  <td class="column-3 text-center">$${param.price}</td>
  <td class="column-4 text-center">${param.quantity}</td>
  <td class="column-5 text-center">$${param.total}</td>
</tr>