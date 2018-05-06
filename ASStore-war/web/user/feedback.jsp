<%-- 
    Document   : feedBack
    Created on : Apr 28, 2018, 9:47:36 AM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(assets/img/feedback_banner.jpg!d); background-position-y: 100%">
        <div class="mask">

        </div>
        <h2 class="l-text2 t-center" style="z-index: 10">
            Feedback
        </h2>
        <p class="m-text13 t-center" style="z-index: 10">
            Share your feedback to us or see what others say about us
        </p>
    </section>
    <div class="banner bgwhite p-t-40 p-b-40">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-8 col-lg-8 m-l-r-auto m-b-20" style="padding: 0">
                    <a href="<c:url value="#"/>" class="bo4 s-text2 p-t-10 p-l-20 p-r-20 p-b-10 hov1 trans-0-4">New feedback</a>
                </div>
            </div>
        </div>
    </div>
    <div class="banner bgwhite p-t-40 p-b-40">
        <c:forEach items="${listFeedback}" var="feedback">
            <jsp:include page="components/feedback/feedbackItem.jsp">
                <jsp:param name="title" value="${feedback.title}"/>
                <jsp:param name="content" value="${feedback.contents}"/>
                <jsp:param name="link" value="https://google.com"/>
            </jsp:include>
        </c:forEach>
    </div>

</t:layout>
