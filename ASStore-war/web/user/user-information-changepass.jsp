<%-- 
    Document   : user-infomation- changepass
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
        <form method="post" action="<c:url value='/User/Updateuserpass'/>">  
          <p style="color: red">${error}</p> <br/>  
          <label>Old password</label>
          <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="password" value="${inputPass}" name="old_pass" placeholder="Old password">
          </div>
          <hr>
          <label>New password</label>
          <div class="bo4 of-hidden size15 m-b-20">
              <input class="sizefull s-text7 p-l-22 p-r-22" type="password" value="${inputNewpass}" name="new_pass" placeholder="New password">
          </div>
          <label>Confirm new password</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="password" name="confirm_new_pass" placeholder="Confirm new password">
          </div>
          <input class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" type="submit" value="Change" />
        </form>
      </div>
    </div>
  </div>
</t:layout>