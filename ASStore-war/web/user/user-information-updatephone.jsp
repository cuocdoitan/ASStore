<%-- 
    Document   : user-infomation-phone
    Created on : May 4, 2018, 9:21:43 PM
    Author     : leminhtung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
  <div class="container">
    <div class="row">
      <div class="col-6 m-l-r-auto m-t-40">
        <form action="">  
          <label>Password</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="pas" placeholder="Password">
          </div>
          <hr>
          <label>New phone number</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="new_phone" placeholder="New phone number">
          </div>
          <label>Confirm new phone number</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="confirm_new_phone" placeholder="Confirm new phone number">
          </div>
          <input class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" type="submit" value="Change" />
        </form>
      </div>
    </div>
  </div>
</t:layout>