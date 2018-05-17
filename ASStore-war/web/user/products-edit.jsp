<%-- 
    Document   : newProduct
    Created on : Apr 29, 2018, 10:32:59 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <section class="bgwhite p-t-70 p-b-50">
        <div class="container">
            <div class="alert alert-warning">
                <strong>WARNING ! After edited, this product will be sent for checking and unavailable for a while</strong>
            </div>
            <form action="<c:url value="/products/edit"/>"  method="post" id="formSubmitProduct">    
                <h2>Edit your product</h2><br><br>
                <input type="hidden" value="${sessionScope.userid}" name="userId"/>
                <input type="hidden" name="productId" value="${product.id}"/>
                <label>Product name</label>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Product name" value="${product.name}"/>
                </div>
                <p style="color: red" id="errName"></p>
                <div class="row">
                    <div class="col-6">
                        <label>Quantity</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="quantity" placeholder="Quantity" value="${product.quantity}"/>
                        </div>
                        <p style="color: red" id="errQuantity"></p>
                    </div>
                    <div class="col-6">
                        <label>Product price (USD)</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="price" placeholder="Product price" value="${product.price}"/>
                        </div>
                        <p style="color: red" id="errPrice"></p>
                    </div>
                </div>
                <label>Anime</label>
                <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
                    <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="anime" id="anime_name" placeholder="Anime name of this product" value="${product.animeId.name}"/>
                </div>
                <p style="color: red" id="errAnime"></p>
                <label>Category</label>
                <div>
                    <select name="category">
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
                    <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="Product description" name="description">${product.description}</textarea>
                </div>
                <p style="color: red" id="errDescription"></p>
                <hr>
                <input type="hidden" id="urlImageProductResource" value="<c:url value="/assets/img/products/"/>"/>
                <input type="hidden" name="image1" class="image" value="${images[0]}"/>
                <input type="hidden" name="image2" class="image" value="${images[1]}"/>
                <input type="hidden" name="image3" class="image" value="${images[2]}"/>
                <input type="hidden" name="image4" class="image" value="${images[3]}"/>
            </form>
            <label>Product images <span style="color: red">*Require at least 1 image, max is 4 images.</span></label>
            <form class="dropzone" method="post" action="<c:url value="/products_uploadFile"/>" enctype="" id="imageUpload">
                <div class="fallback">
                    <input name="images" type="file" multiple accept="image/*"/>
                </div>
            </form>
            <p style="color: red" id="errImage"></p>
            <hr>
            <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" onclick="validateProductSubmit_user()">
                Edit
            </button>
            <div>
                <form action="<c:url value="/products/delete?id=${product.id}"/>" method="post" onsubmit="return confirm('This product will be no more available. Are you sure ?');">
                    <input style="padding:5px; margin: 20px; background-color: red; font-weight: bold" type="submit" value="Delete"/>
                </form>
            </div>

    </section>
    <script>
        function submitEditProductForm() {
            document.getElementById("formEditProduct").submit();
        }
    </script>
</t:layout>
