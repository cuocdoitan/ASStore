<%-- 
    Document   : product-list
    Created on : May 1, 2018, 9:06:11 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:adminLayout>
    <div class="container-fluid">
        <h2 class="m-t-20 m-b-20" align="center">All products on site</h2>
        <div class="row">

            <div class="col-12 m-l-r-auto">
                <div>
                    <div>
                        <select name="SearchBy">
                            <option value="productName">Search by Name</option>
                            <option value="phoneNumber">Search by User phone number</option>
                        </select>
                    </div>
                    <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 100%">
                        <input class="s-text7 size6 p-l-23 p-r-50" type="text" id="input_searchProduct_admin" name="search" placeholder="Type your search keyword here....">

                        <button class="flex-c-m size5 ab-r-m color2 color0-hov trans-0-4" onclick="searchProductAndReloadPage_admin()">
                            <i class="fs-12 fa fa-search" aria-hidden="true"></i>
                        </button>
                    </div>
                </div>
                <div id="listProductContent_admin">
                    <jsp:include page="/admin/components/productList/list.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
</t:adminLayout>
