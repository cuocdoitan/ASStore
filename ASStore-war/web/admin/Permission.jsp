<%-- 
    Document   : Permission
    Created on : May 17, 2018, 9:04:44 AM
    Author     : leminhtung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminLayout>
    <div class="container">
        <div class="row">
            <div class="col-12 m-l-r-auto m-t-20">
                <h1 class="m-b-20">Permission management</h1>
                
                <form action="<c:url value="/admin/user/searchByPhone" />">
                    Search By Phone :
                    <input type="text" name="phone"/>
                    <input type="submit" value="Search"/>
                </form>
                
                <table border="1" style="width: 100%; border-color: #e6e6e6; border: 1px solid #e6e6e6">
                    <thead>
                        <tr style="text-align: center">
                            <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Name</th>
                            <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Phone</th>
                            <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">Moderator</th>
                            <th class="text-center p-t-10 p-l-20 p-r-20 p-b-10">User</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userlist}" var="user" >
                            <tr>
                                <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                                    ${user.firstName} ${user.lastName}
                                </td>
                                <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                                    ${user.phoneNumber}
                                </td>
                                <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                                    <input name="permission${user.id}" class="pcheck" ${user.rolesId.id == 2 ? "checked" : ""} pid="2" uid="${user.id}" url="<c:url value="/admin/permissionServlet" />" type="radio" />
                                </td>
                                <td class="text-center p-t-10 p-l-20 p-r-20 p-b-10">
                                    <input name="permission${user.id}" class="pcheck" ${user.rolesId.id == 3 ? "checked" : ""} pid="3" uid="${user.id}" url="<c:url value="/admin/permissionServlet" />" type="radio" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:adminLayout>