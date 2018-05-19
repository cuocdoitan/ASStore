<%-- 
    Document   : productList
    Created on : Apr 26, 2018, 9:22:42 PM
    Author     : zerox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(<c:url value='/assets/img/product_list_banner.jpg'/>);">
        <div class="mask">

        </div>
        <h2 class="l-text2 t-center" style="z-index: 10">
            Products
        </h2>
        <p class="m-text13 t-center" style="z-index: 10">
            All products from people around the world
        </p>
    </section>

    <section class="bgwhite p-t-55 p-b-65">
        <div class="container">
            <div class="row">

                <div class="col-sm-6 col-md-4 col-lg-3 p-b-50">
                    <div class="leftbar p-r-20 p-r-0-sm">

                        <h4 class="m-text14 p-b-7">
                            Categories
                        </h4>

                        <fieldset id="categoryRadioField">
                            <input type="radio" name="categoryRadio" value="" checked="true"> All<br/> 
                            <c:forEach items="${categories}" var="cate">
                                <li class="p-t-4">
                                  
                                    <input type="radio" name="categoryRadio" <c:if test="${cate.id == vcategory}" >checked</c:if> id="categoryRadio" value="${cate.id}"> ${cate.name}
                                  
                                </li>
                            </c:forEach>
                        </fieldset>

                        <!--  -->
                        <h4 class="m-text14 p-b-32">
                            Filters
                        </h4>

                        <div class="filter-price p-t-22 p-b-50 bo3">
                            <div class="m-text15 p-b-17">
                                Price
                            </div>

                            <div class="wra-filter-bar">
                                <div id="price-bar"></div>
                            </div>

                            <div class="flex-sb-m flex-w p-t-16">
                                <div class="s-text3 p-t-10 p-b-10">
                                    Range: $<span id="value-lower">610</span> - $<span id="value-upper">980</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-8 col-lg-9 p-b-50">
                    <form action="<c:url value="/products/list" />" method="get" id="productListSearch" onsubmit="return form_searchProductList_user();">
                    <div class="flex-row">
                        <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
                            <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="productName" placeholder="Search products by name..." value="${vproductName}">

                            <button class="flex-c-m size5 ab-r-m color2 color0-hov trans-0-4">
                                <i class="fs-12 fa fa-search" aria-hidden="true"></i>
                            </button>
                        </div>
                        <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
                            <input type="text" name="anime_name" id="anime_name" class="s-text7 size6 p-l-23 p-r-50" placeholder="Search products by anime..."  value="${vanimeName}"/>
                            <button class="flex-c-m size5 ab-r-m color2 color0-hov trans-0-4">
                                <i class="fs-12 fa fa-search" aria-hidden="true"></i>
                            </button>
                        </div>
                        <br/><p style="color: red" id="errAnime"></p>
                    </div>
                    <input class="s-text7 size6 p-l-23 p-r-50" type="hidden" name="minPrice" value="${vminPrice}"/>
                    <input class="s-text7 size6 p-l-23 p-r-50" type="hidden" name="maxPrice" value="${vmaxPrice}"/>
                    <input class="s-text7 size6 p-l-23 p-r-50" type="hidden" name="category" value="${vcategory}"/>
                    <div class="flex-sb-m flex-w p-b-35 p-t-20">
                        <div class="flex-w">
                            <div class="rs2-select2 bo4 of-hidden w-size12 m-t-5 m-b-5 m-r-10">
                                <select class="selection-2" name="sorting">
                                    <option value="">Sort by price</option>
                                    <option value="lowToHigh">Low to high</option>
                                    <option value="highToLow">High to low</option>
                                </select>
                            </div>
                        </div>
                        <div class="flex-w">
                            <button id="buttonSearchListProduct">Search</button>
                        </div>
                    </div>
                    </form>
                    <!--  -->

                    <!-- Product -->
                    <div class="row" id="ProductListContent_User">
                        <jsp:include page="/user/components/productList/list.jsp"/>
                    </div>
                    
                </div>
            </div>
        </div>
    </section>
</t:layout>