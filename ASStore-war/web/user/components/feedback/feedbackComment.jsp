<%-- 
    Document   : feedbackComment
    Created on : May 2, 2018, 11:41:16 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-12 col-md-12 col-lg-12 bo4 p-t-20 p-b-20 p-l-20 p-r-20 feedback-comment">
    <!-- block1 -->
    <div class="row">
        <div class="col-sm-11 col-md-11 col-lg-11">
            <div>
                <p style="font-size: 1.1em;">${requestScope.attribute.usersId.firstName} ${requestScope.attribute.usersId.lastName}</p>
                <br/>
                <p style="font-size: 1.1em; color: black;">
                    ${requestScope.attribute.contents}
                </p>
            </div>
        </div>
    </div>
</div>