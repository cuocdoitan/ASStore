<%-- 
    Document   : checkout-success
    Created on : May 16, 2018, 2:40:38 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <h1 class="m-t-30 m-b-20 text-center">Checkout success!</h1>
  <p class="text-center"><img src="<c:url value="/assets/img/thankyou.gif"/>" style="height: 300px" /></p>
  <p class="text-center" style="font-size: 1.1rem">Thank you for shopping with us!</p>
  <div class="m-t-30" style="display: flex; flex-wrap: wrap; justify-content: space-between; align-items: center; width: 500px; margin-left: calc((100% - 500px) / 2)">
    <a href="<c:url value="/orders/list" />" style="width: calc((100% - 10px) / 2)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">View your orders</a>
    <a href="<c:url value="/index" />" style="width: calc((100% - 10px) / 2)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Continue shopping</a>
  </div>
</t:layout>