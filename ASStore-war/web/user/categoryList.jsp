<%-- 
    Document   : categoryList
    Created on : Apr 28, 2018, 9:37:56 AM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

    <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(<c:url value='/assets/img/category_banner.jpg' />); background-position-y: 100%">
        <div class="mask">

        </div>
        <h2 class="l-text2 t-center" style="z-index: 10">
            Categories
        </h2>
        <p class="m-text13 t-center" style="z-index: 10">
            Find your favorite products using our wide range of categories
        </p>
    </section>

    <div class="banner bgwhite p-t-40 p-b-40">
        <div class="container">
            <div class="row">
                <c:forEach items="${listCategory}" var="category">
                    <jsp:include page="components/categoryItem.jsp">
                        <jsp:param name="image" value="${category.picture}"/>
                        <jsp:param name="name" value="${category.name}"/>
                        <jsp:param name="id" value="${category.id}"/>
                    </jsp:include>
                </c:forEach>

            </div>
        </div>
    </div>
</t:layout>