<%-- 
    Document   : user-infomation
    Created on : May 3, 2018, 10:29:01 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <form>  
        PhoneNumber: <input type="number" name="phone" value="${user.phoneNumber}"/> <br/>
        Password: <input type="password" name="pass" value="${user.password}" readonly/> <br/>
        FirstName: <input type="text" name="firstname" value="${user.firstName}" /> <br/> 
        LastName: <input type="text" name="lastname"  value="${user.lastName}"/> <br/> 
        Address: <input type="text" name="address" value="${user.address}"> <br/> 
        Email: <input type="email" name="email" value="${user.email}"/> <br/>  
        <div class="w-size25">
            <!-- Button -->
            <a href="<c:url value='/User/update'/>" class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
                Update infomation
            </a>
        </div>
    </form> 
    <h3>Selling Products</h3>
    <table border="1" style="width: 100%" class="anime-list">
        <thead>
            <tr>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listAvailableProduct}" var="availableProduct" >
                <tr>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        ${availableProduct.name}
                    </td>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        <a href="<c:url value='/products/details?id=${availableProduct.id}'/>" style="color: #3498db">Details</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody> 
    </table>
    <h3>Checking Products</h3>
<table border="1" style="width: 100%" class="anime-list">
        <thead>
            <tr>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listCheckingProduct}" var="checkingProduct" >
                <tr>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        ${checkingProduct.name}
                    </td>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        <a href="<c:url value='/products/details?id=${checkingProduct.id}'/>" style="color: #3498db">Details</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody> 
    </table>
    <h3>Unavailable Products</h3>
<table border="1" style="width: 100%" class="anime-list">
        <thead>
            <tr>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Name</th>
                <th class="text-center p-t-10 p-b-10 p-l-20 p-r-20">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listUnavailableProduct}" var="unavailableProduct" >
                <tr>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        ${unavailableProduct.name}
                    </td>
                    <td class="text-center p-t-10 p-b-10 p-l-20 p-r-20">
                        <a href="<c:url value='/products/details?repair-product=${unavailableProduct.id}'/>" style="color: #3498db">Repair</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody> 
    </table>
</t:layout>