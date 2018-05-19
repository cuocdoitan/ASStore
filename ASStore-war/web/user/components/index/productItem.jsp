<%-- 
    Document   : productItem
    Created on : Apr 25, 2018, 2:37:28 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="post-item p-t-35 fade show active col-3">
      <!-- Block2 -->
      <div class="block2">
        <div class="block2-img wrap-pic-w of-hidden pos-relative block2-labelnew">
            <c:forEach items="${images}" var="image">
                <c:if test="${image.productId.id == param.productId}">
                  <img src="<c:url value='/assets/img/products/${image.urlImage}'/>" height="300px" alt="IMG-PRODUCT">
                </c:if>
            </c:forEach>
          <div class="block2-overlay trans-0-4">
            <div class="block2-btn-addcart w-size1 trans-0-4">
              <!-- Button -->
              <a class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" href="<c:url value="/products/details?id=${param.productId}" />">
                View more
              </a>
            </div>
          </div>
        </div>

        <div class="block2-txt p-t-20">
          <a href="product-detail.html" class="block2-name dis-block s-text3 p-b-5">
            ${param.name}
          </a>

          <span class="block2-price m-text6 p-r-5">
            $${param.price}
          </span>
        </div>
      </div>
  
</div>