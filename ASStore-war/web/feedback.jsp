<%-- 
    Document   : feedBack
    Created on : Apr 28, 2018, 9:47:36 AM
    Author     : zerox
--%>

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
        <jsp:include page="components/feedback/feedbackItem.jsp">
          <jsp:param name="title" value="This is feedback title"/>
          <jsp:param name="content" value="This is feedback content"/>
          <jsp:param name="link" value="https://google.com"/>
        </jsp:include>
      </div>
    </div>
  </div>

</t:layout>
