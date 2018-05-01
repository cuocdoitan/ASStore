<%-- 
    Document   : pureLayout
    Created on : Apr 29, 2018, 12:07:17 AM
    Author     : zerox
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>${title}</title>
    <jsp:include page="/WEB-INF/tags/partials/head.jsp"></jsp:include>
    </head>
    <body class="animsition">
    <jsp:doBody/>
    
    <jsp:include page="/WEB-INF/tags/partials/script.jsp"></jsp:include>
  </body>
</html>