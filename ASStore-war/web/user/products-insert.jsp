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
            <form action="<c:url value="/products/insert"/>"  method="post" id="formSubmitProduct">    
                <h2>Sell your product</h2><br><br>
                <input type="hidden" value="${sessionScope.userid}" name="userId"/>
                <label>Product name</label>
                <div class="bo4 of-hidden size15 m-b-20">
                    <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Product name" required/>
                </div>
                <p style="color: red" id="errName"></p>
                <div class="row">
                    <div class="col-6">
                        <label>Quantity</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="quantity" placeholder="Quantity" required/>
                        </div>
                        <p style="color: red" id="errQuantity"></p>
                    </div>
                    <div class="col-6">
                        <label>Product price (USD)</label>
                        <div class="bo4 of-hidden size15 m-b-20">
                            <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="price" placeholder="Product price" required/>
                        </div>
                        <p style="color: red" id="errPrice"></p>
                    </div>
                </div>
                <label>Anime</label>
                <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
                    <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="anime" id="anime_name" placeholder="Anime name of this product" required/>
                </div>
                <p style="color: red" id="errAnime"></p>
                <label>Category</label>
                <div>
                    <select name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <label>Product description</label>
                <div class="bo4 of-hidden m-b-20">
                    <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="Product description" name="description"></textarea>
                </div>
                <p style="color: red" id="errDescription"></p>
                <hr>
                <input type="hidden" id="urlImageProductResource" value=""/>
                <input type="hidden" class="image"  name="image1" value=""/>
                <input type="hidden" class="image"  name="image2" value=""/>
                <input type="hidden" class="image" name="image3" value=""/>
                <input type="hidden" class="image" name="image4" value=""/>
            </form>
            <label>Product images <span style="color: red">*Require at least 1 image, max is 4 images.</span></label>
            <form class="dropzone" method="post" action="<c:url value="/products_uploadFile"/>" enctype="" id="imageUpload">
                <div class="fallback">
                    <input name="images" type="file" multiple accept="image/*"/>
                </div>
            </form>
            <p style="color: red" id="errImage"></p>
            <hr>
            <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4" id="buttonSubmitForm" onclick="validateProductSubmit_user()">
                Sell product
            </button>
        </div>
    </section>
</t:layout>
