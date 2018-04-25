<%-- 
    Document   : layout
    Created on : Apr 22, 2018, 10:17:54 PM
    Author     : zerox
--%>

<%@tag description="Main Layout tag" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>asd</title>
    <jsp:include page="WEB-INF/tags/partials/head.jsp"></jsp:include>
    </head>
    <body class="animsition">

    <jsp:include page="WEB-INF/tags/components/header.jsp"></jsp:include>
    <jsp:doBody/>
    <!-- Back to top -->
    <div class="btn-back-to-top bg0-hov" id="myBtn">
      <span class="symbol-btn-back-to-top">
        <i class="fa fa-angle-double-up" aria-hidden="true"></i>
      </span>
    </div>

    <!-- Container Selection1 -->
    <div id="dropDownSelect1"></div>

    <jsp:include page="WEB-INF/tags/partials/script.jsp"></jsp:include>
    <script type="text/javascript">
        $(".selection-1").select2({
            minimumResultsForSearch: 20,
            dropdownParent: $('#dropDownSelect1')
        });
    </script>

  </body>
</html>