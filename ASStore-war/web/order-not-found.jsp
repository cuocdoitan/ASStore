<%-- 
    Document   : order-not-found
    Created on : May 13, 2018, 12:05:59 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
  <div class="m-t-50">
    <h1 class="text-center m-b-30">Order #${orderId} doesn't exist!</h1>
    <center>
      <a href='javascript:history.back()'>Go back to previous page</a>
    </center>
  </div>
</t:layout>