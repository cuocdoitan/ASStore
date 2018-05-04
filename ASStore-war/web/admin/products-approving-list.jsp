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
      <table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
          <thead>
            <tr style="text-align: center">
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">ID</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Name</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Description</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Price</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Anime</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Category</th>
              <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Actions</th>
            </tr>
          </thead>
          <tbody id="ListUser">
            <data>
                <c:forEach items="${listApprovingProduct}" var="product">
                    <tr>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.id}</td>
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
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.price}</td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                              ${product.animeId.name}<br/>
                              <img src="<c:url value="/assets/img/anime/${product.animeId.picture}"/>" height="100px" width="100px"/>
                          </td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.categoryId.name}</td>
                          <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                            <a href="#" style="color: #3498db">Approve</a>
                            ||
                            <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                            <a href="<c:url value="/admin/products/deny?id=${product.id}"/>" style="color: #e74c3c">Deny</a>
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
