<%-- 
    Document   : feedback-create
    Created on : May 2, 2018, 4:14:52 PM
    Author     : TRAN HO QUANG
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<t:layout>
    <section class="bgwhite p-t-70 p-b-100">
        <div class="container">
            <h2>NEW FEEDBACK</h2><br><br>
            <form method="post" action="<c:url value='/feedbacks/create' />">
                <label>Title Feedback</label>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="id">
                </div>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="datefeedback">
                </div>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="title" placeholder="Title Feedback">
                </div>
                <label>Content Feedback</label>
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" rows="10" name="content" placeholder="Enter Feedback Content"></textarea>
                </div>
                <br/>
                <br/>
                <div>
                    <input type="submit" value="Add feedback" >
                </div>
                <!--            <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                                Add Feedback
                            </button>
                            <br/>
                            <button style="width: 200px;" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                                Cancel
                            </button>-->
            </form>
        </div>
    </section>
</t:layout>

