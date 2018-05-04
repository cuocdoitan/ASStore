<%-- 
    Document   : user-infomation
    Created on : May 3, 2018, 10:29:01 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
  <form action="">  
    PhoneNumber: <input type="text" name="phone"/>  <a href="">Edit</a> <br/>  
    Password: <input type="password" name="pass" /> <a href="">Edit</a><br/>  
    FirstName: <input type="text" name="firstname"  /> <a href="">Edit</a> <br/> 
    LastName: <input type="text" name="lastname"  /> <a href="">Edit</a> <br/> 
    Address: <input type="text" name="adress" /> <a href="">Edit</a><br/> 
    Email: <input type="email" name="email" /> <a href="">Edit</a><br/>    
  </form>  
</t:layout>