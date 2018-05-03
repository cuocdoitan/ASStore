<%-- 
    Document   : feedback-details
    Created on : May 2, 2018, 3:04:27 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <t:layout>
    <section class="bgwhite p-t-70 p-b-100">
      <div class="container">
        <h2 class="m-b-10">Please fix the bug in cart</h2>
        <label>Posted on 2/2/2018 by UserName</label>
        <div class="content m-b-30 m-t-20">
          <p class="s-text3 p-t-10" style="font-size: 1.2rem">
            Hey admin, I've found this weird bug in the cart section and it's extremely annoying, can you fix it?
          </p>
        </div>
        <h2 class="m-t-20 m-b-20">Comments</h2>
        <div class="bo4 of-hidden m-b-20">
          <textarea style="width: 100%; border: none; padding: 20px" rows="2" placeholder="Post your comments..."></textarea>
        </div>
        <div class="comments">
          <jsp:include page="./components/feedback/feedbackComment.jsp" />
        </div>
        <br/>
      </div>
    </section>
  </t:layout>
</html>
