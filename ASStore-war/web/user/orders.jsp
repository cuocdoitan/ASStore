<%-- 
    Document   : orders
    Created on : May 4, 2018, 9:05:24 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
  <div class="container">
    <div class="row">
      <div class="col-12 m-l-r-auto m-t-20">
        <h1 class="m-b-20">Your orders</h1>
        <table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
          <thead>
            <tr style="text-align: center">
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">ID</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Date</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Status</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Actions</th>
            </tr>
          </thead>
          <tbody>

            <tr>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">1</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">4/5/2018</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Delivering</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                <a href="#" style="color: #007bff">Details</a> || 
                <a href="#" style="color: #c82333">Delete</a>
              </td>
            </tr>
            <tr>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">2</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">1/5/2018</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Delivered</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                <a href="#" style="color: #007bff">Details</a> || 
                <a href="#" style="color: #c82333">Delete</a>
              </td>
            </tr>
            <tr>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">3</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">30/4/2018</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Delivered</td>
              <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                <a href="#" style="color: #007bff">Details</a> || 
                <a href="#" style="color: #c82333">Delete</a>
              </td>
            </tr>
            <c:forEach items="${orders}" var="order">
              <c:if test="${order.enabled}">
                <tr>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${order.id}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${order.createAt}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${order.status ? 'delivered' : 'delivering'}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                    <a href="<c:url value="/orders/details?order=${order.id}" />" style="color: #007bff">Details</a> || 
                    <a href="<c:url value="/orders/delete?order=${order.id}" />" style="color: #c82333">Delete</a>
                  </td>
                </tr>
              </c:if>
            </c:forEach>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</t:layout>
