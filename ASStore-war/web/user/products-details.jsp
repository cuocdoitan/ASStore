<%-- 
    Document   : productDetails
    Created on : Apr 26, 2018, 5:03:59 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>

    <div class="container bgwhite p-t-35 p-b-80">
        <a href="javascript:history.back()" class="s-text2">&lt; Back</a>
        <div class="flex-w flex-sb">
            <div class="w-size13 p-t-30 respon5">
                <div class="wrap-slick3 flex-sb flex-w">
                    <div class="wrap-slick3-dots"></div>

                    <!--Images -->
                    <div class="slick3">
                        <c:forEach items="${images}" var="image">
                            <div class="item-slick3" data-thumb="<c:url value='/assets/img/products/${image}'/>">
                                <div class="wrap-pic-w">
                                    <img src="<c:url value='/assets/img/products/${image}'/>" alt="IMG-PRODUCT">
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                </div>
            </div>


            <div class="w-size14 p-t-30 respon5">
                <h4 class="product-detail-name m-text16 p-b-13">
                    ${product.quantity} x ${product.name}
                </h4>

                <span class="m-text17">
                    ${product.price}
                </span>
                <!-- Quantity add to cart-->
                <div class="p-t-10 p-b-10">
                    <div class="w-size16 flex-w">
                        <div class="flex-w bo5 of-hidden m-r-22 m-t-10 m-b-10">
                            <button class="btn-num-product-down color1 flex-c-m size7 bg8 eff2">
                                <i class="fs-12 fa fa-minus" aria-hidden="true"></i>
                            </button>

                            <input class="size8 m-text18 t-center num-product" type="number" name="num-product" value="1" min="1" max="${product.quantity}">

                            <button class="btn-num-product-up color1 flex-c-m size7 bg8 eff2">
                                <i class="fs-12 fa fa-plus" aria-hidden="true"></i>
                            </button>
                        </div>

                        <div class="btn-addcart-product-detail size9 trans-0-4 m-t-10 m-b-10">
                            <!-- Button -->
                            <button class="flex-c-m sizefull bg1 bo-rad-23 hov0 s-text1 trans-0-4" onclick="addToCart(${product.id}, ${product.price})">
                               Add to cart
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Description-->
                <p class="s-text3 p-t-10">
                    ${product.description}
                </p>
            </div>
        </div>
        <!-- Actions-->
        <br/>
        <c:if test="${product.usersId.id == sessionScope.userid}">
            <a href="<c:url value="/products/edit?id=${product.id}" />">Edit</a>  ||
            <a href="<c:url value="/products/repair-product?id=${product.id}"/>">Repair</a>
        </c:if>
        <!-- Rating -->
        <div id="RatingProductListContent_User">
            <jsp:include page="/user/components/productDetails/rating.jsp" />
        </div>

        <div class="p-l-10"></div>
        <!--        <div class="p-l-10">
                    <div class="product-rating p-t-9">
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                    </div>
                    <p>0/5 stars</p>
                </div>-->
        <br/>
        <br/>
        <br/>
        <hr/>
        <!-- Another products -->
        <h3>Products with the same anime</h3>
        <div class="container">
            <div class="row p-t-20">
                <c:forEach items="${similarProducts}" var="similarProduct">
                    <div class="col-3 m-t-20">
                        <a href="<c:url value='/products/details?id=${similarProduct.id}'/>">
                            <img src="<c:url value='/assets/img/products/${similarProduct.mediaCollection.toArray()[0].urlImage}'/>" width="200px" height='150px'/>
                            <p class="m-t-10 m-b-20">
                                ${similarProduct.name}
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>          
    </div>
            <input name="addCartURL" value="<c:url value="/cart/addToCart" />" type="hidden" />
    <link href="<c:url value="/assets/css/star.css"/>" rel="stylesheet" type="text/css"/>
</t:layout>