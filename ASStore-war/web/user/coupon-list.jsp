<%-- 
    Document   : coupon-list
    Created on : May 21, 2018, 1:36:18 PM
    Author     : zerox
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <div class="container">
    <div class="row">
      <div class="col-12 m-l-r-auto m-t-20">
        <h1 class="m-b-20">Your coupons</h1>
        <a href="<c:url value="/coupon/create" />" class="bg1 bo-rad-23 hov1 s-text1 trans-0-4 p-t-10 p-b-10 p-l-20 p-r-20 m-b-30">New coupon</a>
        <table border="1" class="m-t-30" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
          <thead>
            <tr style="text-align: center">
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">ID</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Coupon</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Product</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Discount</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Expire</th>
              <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${coupons}" var="coupon">
              <tr>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${coupon.id}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${coupon.coupon}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                    <a href="<c:url value="/products/details?id=${coupon.productId.id}" />">
                      ${coupon.productId.name}
                    </a>
                  </td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${coupon.percentage}%</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">${coupon.expireDate}</td>
                  <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                    <a href="<c:url value="/coupon/edit?id=${coupon.id}" />" style="color: #003eff">Edit</a> ||
                    <a href="<c:url value="/coupon/remove?id=${coupon.id}" />" onclick="return confirm('Are you sure?')" style="color: red">Delete</a>
                  </td>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</t:layout>
