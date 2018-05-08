<%-- 
    Document   : newProduct
    Created on : Apr 29, 2018, 10:32:59 PM
    Author     : zerox
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
<form action="<c:url value="/products/insert"/>"  method="post" >
  <section class="bgwhite p-t-70 p-b-100">
    <div class="container">
      <h2>Sell your product</h2><br><br>
      <input type="hidden" value="5" name="userId"/>
      <label>Product name</label>
      <div class="bo4 of-hidden size15 m-b-20">
        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Product name"/>
      </div>
      <div class="row">
        <div class="col-6">
          <label>Quantity</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="quantity" placeholder="Quantity"/>
          </div>
        </div>
        <div class="col-6">
          <label>Product price (USD)</label>
          <div class="bo4 of-hidden size15 m-b-20">
            <input class="sizefull s-text7 p-l-22 p-r-22" type="number" name="price" placeholder="Product price"/>
          </div>
        </div>
      </div>
      <label>Anime</label>
      <div class="search-product pos-relative bo4 m-t-5 m-b-5 m-r-10" style="width: 50%">
        <input class="s-text7 size6 p-l-23 p-r-50" type="text" name="anime" id="anime_name" placeholder="Anime name of this product"/>
      </div>
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
      <hr>
      <input type="hidden" name="image1" value="1111.png"/>
      <input type="hidden" name="image2" value="222.png"/>
      <input type="hidden" name="image3" value="333.png"/>
      <input type="hidden" name="image4" value="444.png"/>
      <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
        Sell product
      </button>
   </section>
</form>
<form class="dropzone" method="post" action="<c:url value="/uploadProductImages"/>" enctype="">
      <div class="container">
        <label>Product images</label>
        <div class="fallback">
            <input name="images" type="file" multiple accept="image/*"/>
        </div>
      </div>
</form>
      <hr>
      
    </div>
</t:layout>
