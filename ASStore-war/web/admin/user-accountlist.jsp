<%-- 
    Document   : user-accountlist
    Created on : May 3, 2018, 8:26:51 PM
    Author     : leminhtung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:adminLayout>
    <form method="get" action="">
        <div class="container">
            <div class="row">
                <div class="col-12 m-t-30">
                    <h1 class="m-b-30 text-center">Account list</h1>
                    <table border="1" style="width: 100%" class="anime-list">
                        <thead>
                            <tr>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Phone</th>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">First name</th>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userlist}" var="user" >
                                <tr>
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                        ${user.phoneNumber}
                                    </td>
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                        ${user.firstName}
                                    </td>  
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                                        <a href="<c:url value='/User/detail?id=${user.id}' />" style="color: #3498db" >Details</a>
                                        ||
                                        <%-- TODOS: IF NOT LAZY ANYMORE, USE SWEETALERT --%>
                                        <a href="<c:url value='/User/delete?id=${user.id}' />" style="color: #e74c3c"  onclick="return confirm('Are you sure ?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </form>
</t:adminLayout>

