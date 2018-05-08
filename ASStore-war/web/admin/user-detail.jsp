<%-- 
    Document   : user-detail
    Created on : May 3, 2018, 10:26:28 PM
    Author     : leminhtung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminLayout>
  <div class="container">
    <div class="row">
      <div class="col-6 m-t-20 m-l-r-auto">
        <div class="m-b-10">
          <img src="<c:url value="/assets/img/avatars/user_1.jpg" />" width="200" height="200">
        </div>
        <div class="m-b-10">
          Phone number: 0922102133
        </div>
        <div class="m-b-10">
          First name: Nguyen
        </div>
        <div class="m-b-10">
          Last name: Viet Hung
        </div> 
        <div class="m-b-10">
          Address: 590 CMT8, District 3, HCM City
        </div>
        <div class="m-b-10">
          Email: viethungax@gmail.com
        </div>
      </div>
    </div>
  </div> 
</t:adminLayout>
