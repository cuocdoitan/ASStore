<%-- 
    Document   : user-infomation
    Created on : May 3, 2018, 10:29:01 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
    <form method="post" action="">  
        PhoneNumber: <input type="number" name="phone" value="${user.phoneNumber}"/>  <a href="">Change PhoneNumber</a> <br/>  
        Password: <input type="password" name="pass" value="${user.password}" readonly/> <a href="">Change Password</a><br/>  
        FirstName: <input type="text" name="firstname" value="${user.firstName}" /> <br/> 
        LastName: <input type="text" name="lastname"  value="${user.lastName}"/> <br/> 
        Address: <input type="text" name="address" value="${user.address}"> <br/> 
        Email: <input type="email" name="email" value="${user.email}"/> <br/>    
    </form> 
    <button onclick="window.location.href = ''">Update</button>
</t:layout>