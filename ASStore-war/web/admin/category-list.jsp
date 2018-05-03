<%-- 
    Document   : category-list
    Created on : May 2, 2018, 4:24:32 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:adminLayout>
    <div class="banner bgwhite p-t-40 p-b-40">
        <div class="container">
            <div class="row">
                <jsp:include page="/admin/components/categoryItem.jsp">
                    <jsp:param name="image" value="shoe.jpg"/>
                    <jsp:param name="name" value="Edit"/>
                    <jsp:param name="name1" value="Delete"/>
                </jsp:include>
            </div>
        </div>
    </div>
</t:adminLayout>

 
