<%-- 
    Document   : user-infomation
    Created on : May 3, 2018, 10:29:01 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>

  <div class="container">
    <div class="row">
      <div class="col-12 m-l-r-auto m-t-30 text-right">
        <div class="w-size25 float-right">
          <form action="<c:url value='/User/logout'/>" method="post">
              <input type="submit" class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4" value="Log Out"/>
          </form>
        </div>
      </div>
      <div class="col-6 m-l-r-auto m-t-40">
        <p style="font-size: 26px; font-weight: bold">User Information:  ${user.lastName}</p>
        <p style="color: red">${error}</p> <br/>
        <label>Phone Number:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="phone" value="${user.phoneNumber}" readonly="true">
        </div>

        <label>Password:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="pass" value="${user.password}" readonly="true">
        </div>
        <label>First Name:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="firstname" value="${user.firstName}" readonly="true">
        </div>
        <label>Last Name:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="lastname"  value="${user.lastName}" readonly="true">
        </div>
        <label>Address:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="address" value="${user.address}" readonly="true">
        </div>
        <label>Email:</label>
        <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="email" name="email" value="${user.email}" readonly="true">
        </div>
      </div>
    </div>
  </div>
  <br/>
  <div class="w-size25" style="margin: 0 auto; width: 250px">
    <!-- Button -->
    <a href="<c:url value='/User/update'/>" class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
      Update information
    </a>
  </div>
  <br/>
  <hr>
  <h3 style="text-align: center" class="m-b-20">Selling Products</h3>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <table border="1" style="width: 100%" class="anime-list">
          <thead>
            <tr>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${listAvailableProduct}" var="availableProduct" >
              <tr>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  ${availableProduct.name}
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  <a href="<c:url value='/products/details?id=${availableProduct.id}'/>" style="color: #3498db">Details</a>
                </td>
              </tr>
            </c:forEach>
          </tbody> 
        </table>
      </div>
      <div class="col-12 m-t-30">
        <h3 style="text-align: center" class="m-b-20">Checking Products</h3>
        <table border="1" style="width: 100%" class="anime-list">
          <thead>
            <tr>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${listCheckingProduct}" var="checkingProduct" >
              <tr>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  ${checkingProduct.name}
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  <a href="<c:url value='/products/details?id=${checkingProduct.id}'/>" style="color: #3498db">Details</a>
                </td>
              </tr>
            </c:forEach>
          </tbody> 
        </table>
      </div>
      <div class="col-12 m-t-30 m-b-30">
        <h3 style="text-align: center" class="m-b-20">Unavailable Products</h3>
        <table border="1" style="width: 100%" class="anime-list">
          <thead>
            <tr>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
              <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${listUnavailableProduct}" var="unavailableProduct" >
              <tr>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  ${unavailableProduct.name}
                </td>
                <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                  <a href="<c:url value='/products/repair-product?id=${unavailableProduct.id}'/>" style="color: #3498db">Repair</a>
                </td>
              </tr>
            </c:forEach>
          </tbody> 
        </table>
      </div>
    </div>
  </div>
</t:layout>