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
    PhoneNumber: <input type="text" name="phone"/>  <a href="">Change PhoneNumber</a> <br/>  
    Password: <input type="password" name="pass" /> <a href="">Change Password</a><br/>  
    FirstName: <input type="text" name="firstname"  /> <br/> 
    LastName: <input type="text" name="lastname"  /> <br/> 
    Address: <input type="text" name="adress" /> <br/> 
    Email: <input type="email" name="email" /> <br/>    
  </form> 
  <button onclick="window.location.href = ''">Update</button>
</t:layout>