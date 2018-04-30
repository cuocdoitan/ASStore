<%-- 
    Document   : categoryItem
    Created on : Apr 26, 2018, 12:35:35 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
  <!-- block1 -->
  <div class="block1 hov-img-zoom pos-relative m-b-30">
    <img src="assets/img/categories/${param.image}" alt="IMG-BENNER">

    <div class="block1-wrapbtn w-size2">
      <!-- Button -->
      <a href="#" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
        ${param.name}
      </a>
    </div>
  </div>
</div>