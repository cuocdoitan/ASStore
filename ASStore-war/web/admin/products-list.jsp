<%-- 
    Document   : product-list
    Created on : May 1, 2018, 9:06:11 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminLayout>
  <div class="container-fluid">
    <h2 class="m-t-20 m-b-20" align="center">All products on site</h2>
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
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Status</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Name</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Description</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Price</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Actions</th>
            </tr>
          </thead>
          <tbody id="ListUser">
            <data>
                <c:forEach items="${listProduct}" var="product">
                    <tr>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.id}</td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                              <c:choose>
                                  <c:when test="${product.status == 0}">
                                      Checking
                                  </c:when>
                                  <c:when test="${product.status == 1}">
                                      Available
                                  </c:when>
                                  <c:when test="${product.status == 2}">
                                      Unavailable
                                  </c:when>
                              </c:choose>
                          </td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.name}</td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.description}</td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                              <c:forEach items="${listImages}" var="images">
                                  <c:if test="${product.id == images.key}">
                                      <c:forEach items="${images.value}" var="image">
                                          <img src="<c:url value="/assets/img/products/${image}"/>" height="50px" width="50px"/>
                                      </c:forEach>
                                  </c:if>
                              </c:forEach>
                            <!--<img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>" height="100px"/>-->
                          </td>
                          
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                            <a href="#" style="color: #3498db">Details</a>
                            ||
                            <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                            <a href="#" style="color: #e74c3c"  onclick="return confirm('Are you sure ?')">Delete</a>
                          </td>
                    </tr>
                </c:forEach>
            </data>    
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</t:adminLayout>
