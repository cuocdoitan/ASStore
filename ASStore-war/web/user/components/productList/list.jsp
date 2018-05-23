<%-- 
    Document   : productItem
    Created on : Apr 26, 2018, 10:16:55 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .pagination{display:inline-block;padding-left:0;margin:20px 0;border-radius:4px}.pagination>li{display:inline}.pagination>li>a,.pagination>li>span{position:relative;float:left;padding:6px 12px;margin-left:-1px;line-height:1.42857143;color:#337ab7;text-decoration:none;background-color:#fff;border:1px solid #ddd}.pagination>li:first-child>a,.pagination>li:first-child>span{margin-left:0;border-top-left-radius:4px;border-bottom-left-radius:4px}.pagination>li:last-child>a,.pagination>li:last-child>span{border-top-right-radius:4px;border-bottom-right-radius:4px}.pagination>li>a:focus,.pagination>li>a:hover,.pagination>li>span:focus,.pagination>li>span:hover{z-index:2;color:#23527c;background-color:#eee;border-color:#ddd}.pagination>.active>a,.pagination>.active>a:focus,.pagination>.active>a:hover,.pagination>.active>span,.pagination>.active>span:focus,.pagination>.active>span:hover{z-index:3;color:#fff;cursor:default;background-color:#337ab7;border-color:#337ab7}.pagination>.disabled>a,.pagination>.disabled>a:focus,.pagination>.disabled>a:hover,.pagination>.disabled>span,.pagination>.disabled>span:focus,.pagination>.disabled>span:hover{color:#777;cursor:not-allowed;background-color:#fff;border-color:#ddd}.pagination-lg>li>a,.pagination-lg>li>span{padding:10px 16px;font-size:18px;line-height:1.3333333}.pagination-lg>li:first-child>a,.pagination-lg>li:first-child>span{border-top-left-radius:6px;border-bottom-left-radius:6px}.pagination-lg>li:last-child>a,.pagination-lg>li:last-child>span{border-top-right-radius:6px;border-bottom-right-radius:6px}.pagination-sm>li>a,.pagination-sm>li>span{padding:5px 10px;font-size:12px;line-height:1.5}.pagination-sm>li:first-child>a,.pagination-sm>li:first-child>span{border-top-left-radius:3px;border-bottom-left-radius:3px}.pagination-sm>li:last-child>a,.pagination-sm>li:last-child>span{border-top-right-radius:3px;border-bottom-right-radius:3px}
    /*---------- star rating ----------*/
    .star-rating {
        display: flex;
        align-items: center;
        font-size: 2em;
        justify-content: left;
        margin-top: 10px;
        margin-bottom: 10px;
    }
    .back-stars {
        display: flex;
        color: #bb5252;
        position: relative;
        text-shadow: 4px 4px 10px #843a3a;
    }
    .front-stars {
        display: flex;
        color: #FFBC0B;
        overflow: hidden;
        position: absolute;
        text-shadow: 2px 2px 5px #d29b09;
        top: 0;
    </style>
    <c:forEach items="${listProduct}" var="product">
        <div class="col-sm-12 col-md-6 col-lg-4 p-b-50">
            <!-- Block2 -->
            <a href="<c:url value="/products/details?id=${product.id}"/>">
                <div class="block2">
                    <div class="block2-img wrap-pic-w of-hidden pos-relative">
                        <c:choose>
                            <c:when test="${product.quantity == 0}">
                                <img src="<c:url value='/assets/img/products/outofstock.jpg'/>" alt="IMG-PRODUCT">
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${images}" var="image">
                                    <c:if test="${image.productId.id == product.id}">
                                        <img src="<c:url value='/assets/img/products/${image.urlImage}'/>" alt="IMG-PRODUCT">
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="block2-txt p-t-20">
                        <div class="star-rating" title="75%">
                            <div class="back-stars">
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>
                                <i class="fa fa-star" aria-hidden="true"></i>

                                <div class="front-stars">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                        <p><span class="block2-price m-text6 p-r-5 totalRating">${product.averageStars()}</span>/5 stars</p>
                        <span class="block2-price m-text6 p-r-5">${product.name}</span><br/>
                        <span class="block2-price m-text6 p-r-5">${product.price} $</span>
                    </div>
                </div>
            </a>    
        </div>
    </c:forEach>


    <c:choose>
        <c:when test="${selectedPage - 2 le 1}"><!--IN RANGE OF FIRST--><!--1 2 3 4 5-->

            <c:choose>
                <c:when test="${selectedPage + 2 ge numberOfPage}"><!--IN RANGE OF LAST-->
                    <ul class="pagination pagination-sm" style="float: right">
                            <c:forEach begin="1" end="${numberOfPage}" var="page">
                                <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise><!--OUT RANGE OF LAST--><!--1 2 3 4 5 .....99-->
                        <ul class="pagination pagination-sm" style="float: right">
                            <c:forEach begin="1" end="${numberOfPage}" var="page">
                                <c:choose>
                                    <c:when test="${page gt (selectedPage + 2)}"><!--part out of range last-->
                                        <c:choose>
                                            <c:when test="${page == numberOfPage}"><!--show last page-->
                                                <li><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${(page - 1) == (selectedPage + 2)}"><!--check if the previous page is range of first-->
                                                        <li><a>...</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <!--do nothing-->    
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                        </c:otherwise>
                                    </c:choose>

                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>

            </c:when>
            <c:otherwise><!--OUT RANGE OF FIRST--><!--1 .....95 96 97 98 99-->

                <c:choose>
                    <c:when test="${selectedPage + 2 ge numberOfPage}"><!--IN RANGE OF LAST-->
                        <ul class="pagination pagination-sm" style="float: right">
                            <c:forEach begin="1" end="${numberOfPage}" var="page">
                                <c:choose>
                                    <c:when test="${page lt (selectedPage - 2)}"><!--part out of range first-->
                                        <c:choose>
                                            <c:when test="${page == 1}"><!--show first page-->
                                                <li><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${(page + 1) == (selectedPage - 2)}"><!--check if the previous page is in part out of range first-->
                                                        <li><a>...</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <!--do nothing-->
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise><!--OUT RANGE OF LAST--><!--1...25 26 27 28 29...99-->
                        <ul class="pagination pagination-sm" style="float: right">
                            <c:forEach begin="1" end="${numberOfPage}" var="page">
                                <c:choose>
                                    <c:when test="${page lt (selectedPage - 2)}"><!--part out of range first-->
                                        <c:choose>
                                            <c:when test="${page == 1}"><!--show first page-->
                                                <li><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${(page + 1) == (selectedPage - 2)}"><!--check if the previous page is in part out of range first-->
                                                        <li><a>...</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <!--do nothing-->
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:when test="${page gt (selectedPage + 2)}"><!--part out of range last-->
                                        <c:choose>
                                            <c:when test="${page == numberOfPage}"><!--show last page-->
                                                <li><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${(page - 1) == (selectedPage + 2)}"><!--check if the previous page is range of first-->
                                                        <li><a>...</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <!--do nothing-->    
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <li <c:if test="${selectedPage == page}">class="active"</c:if>><a href="<c:url value="/products/list?${queryString}&page=${page}"/>">${page}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>

            </c:otherwise>            
        </c:choose>

