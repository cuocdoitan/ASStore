<%-- 
    Document   : product-approving-list
    Created on : May 1, 2018, 7:52:51 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminLayout>
    <div class="container-fluid">
        <h2 class="m-t-20 m-b-20" align="center">All pending products</h2>
        <div class="row">
            <div class="col-12 m-l-r-auto">
                <div id="listApprovingProductContent_admin">
                    <jsp:include page="/admin/components/productApprovingList/list.jsp"/>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/assets/js/custom-showList.js" />" type="text/javascript"></script>
</t:adminLayout>
