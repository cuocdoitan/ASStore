<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
    <thead>
        <tr style="text-align: center">
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">ID</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Status</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Name</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Description</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Anime</th>
            <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Category</th>
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
                            <span style="color: blue">Checking</span>
                        </c:when>
                        <c:when test="${product.status == 1}">
                            <span style="color: green">Available</span>
                        </c:when>
                        <c:when test="${product.status == 2}">
                            <span style="color: yellow">Unavailable</span>
                        </c:when>
                    </c:choose>
                </td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.name}</td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.description}</td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                    <c:forEach items="${product.mediaCollection}" var="media">
                        <img src="<c:url value="/assets/img/products/${media.urlImage}"/>" height="50px" width="50px"/>
                    </c:forEach>
                </td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                    ${product.animeId.name}<br/>
                    <img src="<c:url value="/assets/img/anime/${product.animeId.picture}"/>" height="100px" width="100px"/>
                </td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${product.categoryId.name}</td>
                <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                    <a href="<c:url value="/admin/products/details?id=${product.id}"/>" style="color: #3498db">Details</a>
                    ||
                    <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                    <a href="<c:url value="/admin/products/delete?id=${product.id}"/>" style="color: #e74c3c"  onclick="return confirm('This product will be no more available. Are you sure ?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </data>    
</tbody>
</table>