<%-- 
    Document   : script
    Created on : Apr 22, 2018, 10:23:29 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--===============================================================================================-->

<script type="text/javascript" src="<c:url value='/assets/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/assets/vendor/jqueryui/jquery-ui.min.js'/>"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/animsition/js/animsition.min.js'/>"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/bootstrap/js/popper.js'/>"></script>
<script type="text/javascript" src="<c:url value='/assets/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/select2/select2.min.js'/>"></script>
<script type="text/javascript">
  $(".selection-1").select2({
    minimumResultsForSearch: 20,
    dropdownParent: $('#dropDownSelect1')
  });

  $(".selection-2").select2({
    minimumResultsForSearch: 20,
    dropdownParent: $('#dropDownSelect2')
  });
</script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/slick/slick.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/assets/js/slick-custom.js'/>"></script>
<script type="text/javascript">
  $(".slider").not('.slick-initialized').slick()
</script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/sweetalert/sweetalert.min.js'/>"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="<c:url value='/assets/vendor/noui/nouislider.min.js'/>"></script>
<script src="<c:url value='/assets/js/dropzone.js'/>"></script>
<script src="<c:url value='/assets/js/main.js'/>"></script>
<script src="<c:url value='/assets/js/custom.js'/>"></script>
<script src="<c:url value='/assets/js/feedback.js'/>"></script>
<script src="<c:url value='/assets/js/checkout.js'/>"></script>
<script src="<c:url value='/assets/js/product.js'/>"></script>
<script src="<c:url value='/assets/js/permission.js'/>"></script>
<script src="<c:url value='/assets/js/addToCart.js'/>"></script>
<script src="<c:url value='/assets/js/coupon.js'/>"></script>