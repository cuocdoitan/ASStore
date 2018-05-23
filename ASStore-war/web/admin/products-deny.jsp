<%-- 
    Document   : newProduct
    Created on : Apr 29, 2018, 10:32:59 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:adminLayout>
    <section class="bgwhite p-t-70 p-b-100">
        <div class="container">
            <form method="POST" action="<c:url value='/admin/products/deny'/>" onsubmit="return validateNote(event);">
                <h2>Send Note for this Product</h2><br><br>
                <input type="hidden" name="productId" value="${product.id}"/>
                <label>Product name</label>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Product name" readonly="true" value="${product.name}">
                </div>
                <div class="row">
                    <div class="col-6">
                        <label>Quantity</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="name" placeholder="Quantity" readonly="true" value="${product.quantity}">
                        </div>
                    </div>
                    <div class="col-6">
                        <label>Product price (USD)</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="name" placeholder="Product price" readonly="true"  value="${product.price}">
                        </div>
                    </div>
                </div>
                <label>Anime</label>
                <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
                    <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="anime" id="anime_name" placeholder="Anime name of this product"  readonly="true" value="${product.animeId.name}"/>
                </div>
                <label>Category</label>
                <div>
                    <select name="category"  disabled="true">
                        <c:forEach items="${categories}" var="category">
                            <c:choose>
                                <c:when test="${category.id == product.categoryId.id}">
                                    <option value="${category.id}" selected="true">${category.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.id}">${category.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label>Product description</label>
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="Product description" readonly="true">${product.description}</textarea>
                </div>
                <hr>
                <label>Product images</label>
                <div>
                    <c:forEach items="${product.mediaCollection}" var="media">
                        <img src="<c:url value="/assets/img/products/${media.urlImage}"/>" height="50px" width="50px"/>
                    </c:forEach>
                </div>
                <hr>
                <label style="color: red">**Note**</label>
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" rows="10" name="alertNote" placeholder="Note for product"></textarea>
                </div>
                <p id="errNote" style="padding: 5px;color: red"></p>
                <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
                    Deny
                </button>
                <br/>
            </form>
            <form action="<c:url value="/admin/products/delete?id=${product.id}"/>" method="post" onsubmit="return confirm('This product will be no more available. Are you sure ?');">
                <input style="padding:5px; margin: 20px; background-color: red; font-weight: bold" type="submit" value="Delete"/>
            </form>
        </div>
    </section>
</t:adminLayout>
