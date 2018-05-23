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
          Phone number: ${userid.phoneNumber}
        </div>
        <div class="m-b-10">
          First name: ${userid.firstName}
        </div>
        <div class="m-b-10">
          Last name: ${userid.lastName}
        </div> 
        <div class="m-b-10">
          Address: ${userid.address}
        </div>
        <div class="m-b-10">
          Email: ${userid.email}
        </div>
        
      </div>
    </div>
  </div> 
</t:adminLayout>
