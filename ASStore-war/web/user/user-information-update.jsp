<%-- 
    Document   : user-infomation
    Created on : May 3, 2018, 10:29:01 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <form method="post" action="<c:url value='/User/Updateuser'/>">
        <div class="container">
            <div class="row">
                <div class="col-6 m-l-r-auto m-t-40">

                    <p style="color: red">${error}</p> <br/>
                    <label>Phone Number: <a href="<c:url value='/User/updatephone'/>">Change Phone Number</a></label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="phone" value="${user.phoneNumber}" readonly="true">
                    </div>
                    <label>Password: <a href="<c:url value='/User/updatepass'/>">Change Password</a></label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="pass" value="${user.password}" readonly="true">
                    </div>
                    <label>First Name:</label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="firstname" value="${user.firstName}">
                    </div>
                    <label>Last Name:</label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="lastname"  value="${user.lastName}">
                    </div>
                    <label>Address:</label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="address" value="${user.address}">
                    </div>
                    <label>Email:</label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="email" name="email" value="${user.email}">
                    </div>
                </div>
            </div>
        </div>
        <div class="w-size25" style="margin: 0 auto; width: 100px">
            <!-- Button -->
            <button class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
                Change
            </button>
        </div>
        <br/>
        <br/>
    </form> 

</t:layout>