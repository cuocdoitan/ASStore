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
            <h1 class="text-center m-t-20 m-b-20">Category list</h1>
            <p style="color: red">${error}</p>
            <br/>
        </div>
        <div class="col-sm-8 col-md-8 col-lg-8 m-l-r-auto m-b-20">
            <a href="<c:url value="/admin/category/create"/>" class="bo4 s-text2 p-t-10 p-l-20 p-r-20 p-b-10 hov1 trans-0-4">New Category</a>
        </div>
        <table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
            <thead>
                <tr style="text-align: center">
                    <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">ID</th>
                    <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Name</th>
                    <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Image</th>
                    <th class="text-center p-t-20 p-l-20 p-r-20 p-b-20">Actions</th>
                </tr>
            </thead>
            <tbody id="ListCategoryAdmin">
            <data>
                <c:forEach items="${listCategory}" var="category">
                    <tr>
                        <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${category.id}</td>
                        <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">${category.name}</td>
                        <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                            <img src="<c:url value="/assets/img/categories/${category.picture}"/>" height="100px" width="100px"/>
                        </td>
                        <td class="text-center p-t-20 p-l-20 p-r-20 p-b-20">
                            <a href="<c:url value="/admin/category/edit?id=${category.id}"/>" style="color: #3498db">Edit</a>
                            ||
                            <form method="post" action="<c:url value='/admin/category/delete'/>" id="formDeleteCategory">
                                <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                                <input type="hidden" name="id" value="${category.id}"/>
                                <p style="color: #e74c3c"  onclick="lastCheck(this)">Delete</p>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </data>
        </table>
    </div>
    <script>
        function lastCheck(e) {
            if (confirm('Are you sure ?')) {
                console.log(e)
                e.parentElement.submit();
            } else {
                return false;
            }
        }
    </script>
</t:adminLayout>


