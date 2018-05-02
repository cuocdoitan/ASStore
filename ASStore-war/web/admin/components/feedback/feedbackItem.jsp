<%-- 
    Document   : feedbackItem
    Created on : Apr 28, 2018, 9:55:31 AM
    Author     : zeroxTitle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-8 col-md-8 col-lg-8 m-l-r-auto bo4 p-t-30 p-l-20 p-r-20 feedback-item">
  <!-- block1 -->
  <div class="row">
    <div class="col-sm-2 col-md-2 col-lg-2">
      <img src="assets/img/avatars/user_1.jpg" alt="" class="avatar">
    </div>
    <div class="col-sm-10 col-md-10 col-lg-10">
      <div class="pos-relative m-b-20">
        <div class="s-text2">
          <h5>${param.title}</h5>
        </div>
      </div>
      <div class="m-b-20">
        <p style="font-size: 1.1em; color: black">${param.content}</p>
      </div>
      <div class="m-b-20">
        <a href="${param.link}" class="bo4 s-text2 p-t-10 p-l-20 p-r-20 p-b-10 float-right hov1 trans-0-4">read more</a>
        <div style="clear: both"></div>
      </div>
    </div>
  </div>
</div>