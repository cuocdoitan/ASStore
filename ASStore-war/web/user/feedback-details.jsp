<%-- 
    Document   : feedback-details
    Created on : May 2, 2018, 3:04:27 PM
    Author     : TRAN HO QUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <t:layout>

        <section class="bgwhite p-t-70 p-b-100">
            <div class="container">

                <c:if test="${sessionScope.role == roleId}">
                    <a href="<c:url value='/feedbacks/deletefeeback?fbsid=${feedback.id}' />" style="color: #e74c3c"  onclick="return confirm('Are you sure ?')">
                        <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                            Close Feedback
                        </button>
                    </a>
                </c:if>

                <br/>
                <br/>
                <c:if test="${sessionScope.role == roleId}">
                    <div>
                        <p>STT:</p> <input type="text" id="idfeedbackdelete" name="idfeedbackdelete" value="${feedback.id}" readonly="true">
                    </div>
                </c:if>
                <br/>
                <h2 class="m-b-10">${feedback.title}</h2>
                <label>${feedback.createAt}</label>
                <br/>
                <label>Poster: ${feedback.usersId.firstName} ${feedback.usersId.lastName}</label>
                <div class="content m-b-30 m-t-20">
                    <p class="s-text3 p-t-10" style="font-size: 1.2rem">
                        ${feedback.contents}
                    </p>
                </div>
                <div class="comments" id="listcomment">
                    <jsp:include page="/user/components/feedback/feedbackCommentList.jsp" />
                </div>
            </div>
            <br/>
            <form id="nameFB" method="post" action="<c:url value='/feedbacks/comment'/>">
                <div>
                    <input type="hidden" id="idfeedback" name="idfeedback" value="${feedback.id}" >
                </div>
                <h2 class="m-t-20 m-b-20">Comments</h2>
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" id="comment" name="comment" rows="2" placeholder="Post your comments..."></textarea>
                </div>
                <!--                    <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                                        Comment
                                    </button>-->
            </form>

            <br/>
            <br/>
        </div>
    </section>
</t:layout>
</html>
