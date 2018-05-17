<%-- 
    Document   : productItem
    Created on : Apr 26, 2018, 10:16:55 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listProduct}" var="product">
    <div class="col-sm-12 col-md-6 col-lg-4 p-b-50">
        <!-- Block2 -->
        <a href="<c:url value="/products/details?id=${product.id}"/>">
            <div class="block2">
                <div class="block2-img wrap-pic-w of-hidden pos-relative">
                    <c:forEach items="${images}" var="image">
                        <c:if test="${image.productId.id == product.id}">
                            <img src="<c:url value='/assets/img/products/${image.urlImage}'/>" alt="IMG-PRODUCT">
                        </c:if>
                    </c:forEach>
                </div>

                <div class="block2-txt p-t-20">
                    <a href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
                        ${product.name}
                    </a>
                    <span class="block2-price m-text6 p-r-5">
                        $${product.price}
                    </span>
                </div>
            </div>
        </a>    
    </div>
</c:forEach>

