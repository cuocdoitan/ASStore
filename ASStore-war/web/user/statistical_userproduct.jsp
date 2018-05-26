<%-- 
    Document   : statistical_user
    Created on : May 25, 2018, 11:02:21 AM
    Author     : leminhtung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-12 m-t-30">
                    <h2 class="m-b-30 text-center">User ratings</h2>
                    <table border="1" style="width: 100%" class="anime-list">
                        <thead>
                            <tr>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Rank</th>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">User</th>
                                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Number of products</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userNumberOfProducts}" var="numberP" varStatus="loop">
                                <tr>
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">${loop.index + 1}</td>
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">${users.get(numberP.key).firstName} ${users.get(numberP.key).lastName}</td>
                                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">${numberP.value}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</t:layout>
