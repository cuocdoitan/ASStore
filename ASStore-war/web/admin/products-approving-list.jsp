<%-- 
    Document   : product-approving-list
    Created on : May 1, 2018, 7:52:51 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminLayout>
  <div class="container-fluid">
    <h2 class="m-t-20 m-b-20" align="center">All pending products</h2>
    <div class="row">
      <div class="col-12 m-l-r-auto">
        <form class="m-b-20 m-t-20" method="get" action="#">
          <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 100%">
            <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="productId" placeholder="Search products by ID...">

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
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Description</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Price</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Actions</th>
            </tr>
          </thead>
          <tbody id="ListUser">
            <tr>
              <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">1</td>
              <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">FairyTail Shoes</td>
              <td class="p-t-20 p-l-20 p-r-20 p-b-20">
                akafan ifaifandis dasidasikt jdfjdk fdjfdjrl ljawjdl hgfgdfg
                </div>
              <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>" height="100px"/>
              </td>
              <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">545.65</td>
              <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                <a href="#" style="color: #3498db">Approve</a>
                ||
                <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                <a href="#" style="color: #e74c3c"  onclick="return confirm('Are you sure ?')">Deny</a>
              </td>
            </tr>
        </table>
      </div>
    </div>
    <%--<c:forEach items="${listProduct}" var="product">
        <tr>
            <td>${product.productId}</div>
            <td>${product.productName}</div>
            <td>
                <img src="<c:url value="/resources/IMAGE/${product.productImage}"/>"  height="100px" width="100px"/>
            </div>
            <td>${product.productPrice}</div>
            <td>${product.productDetails}</div>
            <td>${product.manufacturerId.manufacturerName}</div>
            <td>
                <a href="<c:url value="/product/details?id=${product.productId}"/>">Details</a> |
                <a href="<c:url value="/product/update?id=${product.productId}"/>">Edit</a>
                <form action="<c:url value="/product/delete?id=${product.productId}"/>" method="post" onsubmit="return lastCheck()">
                    <input type="submit" value="Delete" style="background-color: red"/>
                </form>
            </div>
        </div>
    </c:forEach>--%>
  </div>
</div>
</t:adminLayout>