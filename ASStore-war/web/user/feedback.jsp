<%-- 
    Document   : feedBack
    Created on : Apr 28, 2018, 9:47:36 AM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <style>
        .pagination{display:inline-block;padding-left:0;margin:20px 0;border-radius:4px}.pagination>li{display:inline}.pagination>li>a,.pagination>li>span{position:relative;float:left;padding:6px 12px;margin-left:-1px;line-height:1.42857143;color:#337ab7;text-decoration:none;background-color:#fff;border:1px solid #ddd}.pagination>li:first-child>a,.pagination>li:first-child>span{margin-left:0;border-top-left-radius:4px;border-bottom-left-radius:4px}.pagination>li:last-child>a,.pagination>li:last-child>span{border-top-right-radius:4px;border-bottom-right-radius:4px}.pagination>li>a:focus,.pagination>li>a:hover,.pagination>li>span:focus,.pagination>li>span:hover{z-index:2;color:#23527c;background-color:#eee;border-color:#ddd}.pagination>.active>a,.pagination>.active>a:focus,.pagination>.active>a:hover,.pagination>.active>span,.pagination>.active>span:focus,.pagination>.active>span:hover{z-index:3;color:#fff;cursor:default;background-color:#337ab7;border-color:#337ab7}.pagination>.disabled>a,.pagination>.disabled>a:focus,.pagination>.disabled>a:hover,.pagination>.disabled>span,.pagination>.disabled>span:focus,.pagination>.disabled>span:hover{color:#777;cursor:not-allowed;background-color:#fff;border-color:#ddd}.pagination-lg>li>a,.pagination-lg>li>span{padding:10px 16px;font-size:18px;line-height:1.3333333}.pagination-lg>li:first-child>a,.pagination-lg>li:first-child>span{border-top-left-radius:6px;border-bottom-left-radius:6px}.pagination-lg>li:last-child>a,.pagination-lg>li:last-child>span{border-top-right-radius:6px;border-bottom-right-radius:6px}.pagination-sm>li>a,.pagination-sm>li>span{padding:5px 10px;font-size:12px;line-height:1.5}.pagination-sm>li:first-child>a,.pagination-sm>li:first-child>span{border-top-left-radius:3px;border-bottom-left-radius:3px}.pagination-sm>li:last-child>a,.pagination-sm>li:last-child>span{border-top-right-radius:3px;border-bottom-right-radius:3px}
    </style>
    <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(<c:url value='/assets/img/feedback_banner.jpg'/>); background-position-y: 100%">
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
                    <a href="<c:url value="/feedbacks/create"/>" class="bo4 s-text2 p-t-10 p-l-20 p-r-20 p-b-10 hov1 trans-0-4">New feedback</a>
                </div>
            </div>
        </div>
    </div>
    <div class="banner bgwhite p-t-40 p-b-40">
        <c:forEach items="${listFeedback}" var="feedback">
            <jsp:include page="components/feedback/feedbackItem.jsp">
                <jsp:param name="title" value="${feedback.title}"/>
                <jsp:param name="content" value="${feedback.contents}"/>
                <jsp:param name="link" value="/feedbacks/details?id=${feedback.id}"/>
            </jsp:include>
        </c:forEach>
        <div class="col-sm-8 col-md-8 col-lg-8 m-l-r-auto p-t-30 p-l-20 p-r-20" style="display: flex">
            <c:choose>
                <c:when test="${selectedPage - 2 le 1}"><!--IN RANGE OF FIRST--><!--1 2 3 4 5-->

                    <c:choose>
                        <c:when test="${selectedPage + 2 ge numberOfPage}"><!--IN RANGE OF LAST-->
                            <ul class="pagination pagination-sm" style="float: right">
                                <c:forEach begin="1" end="${numberOfPage}" var="page">
                                    <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                    </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise><!--OUT RANGE OF LAST--><!--1 2 3 4 5 .....99-->
                            <ul class="pagination pagination-sm" style="float: right">
                                <c:forEach begin="1" end="${numberOfPage}" var="page">
                                    <c:choose>
                                        <c:when test="${page gt (selectedPage + 2)}"><!--part out of range last-->
                                            <c:choose>
                                                <c:when test="${page == numberOfPage}"><!--show last page-->
                                                    <li><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${(page - 1) == (selectedPage + 2)}"><!--check if the previous page is range of first-->
                                                            <li><a>...</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <!--do nothing-->    
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                            </c:otherwise>
                                        </c:choose>

                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>

                </c:when>
                <c:otherwise><!--OUT RANGE OF FIRST--><!--1 .....95 96 97 98 99-->

                    <c:choose>
                        <c:when test="${selectedPage + 2 ge numberOfPage}"><!--IN RANGE OF LAST-->
                            <ul class="pagination pagination-sm" style="float: right">
                                <c:forEach begin="1" end="${numberOfPage}" var="page">
                                    <c:choose>
                                        <c:when test="${page lt (selectedPage - 2)}"><!--part out of range first-->
                                            <c:choose>
                                                <c:when test="${page == 1}"><!--show first page-->
                                                    <li><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${(page + 1) == (selectedPage - 2)}"><!--check if the previous page is in part out of range first-->
                                                            <li><a>...</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <!--do nothing-->
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise><!--OUT RANGE OF LAST--><!--1...25 26 27 28 29...99-->
                            <ul class="pagination pagination-sm" style="float: right">
                                <c:forEach begin="1" end="${numberOfPage}" var="page">
                                    <c:choose>
                                        <c:when test="${page lt (selectedPage - 2)}"><!--part out of range first-->
                                            <c:choose>
                                                <c:when test="${page == 1}"><!--show first page-->
                                                    <li><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${(page + 1) == (selectedPage - 2)}"><!--check if the previous page is in part out of range first-->
                                                            <li><a>...</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <!--do nothing-->
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:when test="${page gt (selectedPage + 2)}"><!--part out of range last-->
                                            <c:choose>
                                                <c:when test="${page == numberOfPage}"><!--show last page-->
                                                    <li><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${(page - 1) == (selectedPage + 2)}"><!--check if the previous page is range of first-->
                                                            <li><a>...</a></li>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <!--do nothing-->    
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/feedbacks/list?${queryString}&page=${page}"/>">${page}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>

                </c:otherwise>            
            </c:choose>
        </div>
    </div>
</t:layout>
