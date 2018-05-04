<%-- 
    Document   : category-list
    Created on : May 2, 2018, 4:24:32 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:adminLayout>
  <div class="row">
    <div class="col-12 m-l-r-auto">
      <form class="m-b-20 m-t-20" method="get" action="#">
        <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 100%">
          <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="productId" placeholder="Search category by name...">

          <button class="flex-c-m size5 ab-r-m color2 color0-hov trans-0-4" type="submit">
            <i class="fs-12 fa fa-search" aria-hidden="true"></i>
          </button>
        </div>
      </form>
      <table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
        <thead>
          <tr style="text-align: center">
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">ID</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Name</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Actions</th>
          </tr>
        </thead>
        <tbody id="ListUser">
          <tr>
            <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">1</td>
            <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Shoes</td>
            <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
              <img src="<c:url value="/assets/img/categories/shoe.jpg"/>" height="100px"/>
            </td>
            <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
              <a href="#" style="color: #3498db">Edit</a>
              ||
              <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
              <a href="#" style="color: #e74c3c"  onclick="return confirm('Are you sure ?')">Delete</a>
            </td>
          </tr>
      </table>
    </div>
  </div>
</t:adminLayout>

