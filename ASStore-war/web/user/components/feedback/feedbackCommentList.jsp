<%-- 
    Document   : feedbackList
    Created on : May 12, 2018, 10:22:58 AM
    Author     : TRAN HO QUANG
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach items="${listComment}" var="comment">
    <%--<jsp:include page="./feedbackComment.jsp" >
        <jsp:param name="name" value="${comment.usersId}" />
        <jsp:param name="content" value="${comment.contents}" />
    </jsp:include>--%>
    <c:set var="attribute" value="${comment}" scope="request"/>
    <c:import url="./components/feedback/feedbackComment.jsp"/>
</c:forEach>