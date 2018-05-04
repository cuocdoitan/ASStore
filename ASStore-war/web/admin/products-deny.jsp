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
      <h2>Send Note for this Product</h2><br><br>
      
      <label>Product name</label>
      <div class="bo4 of-hidden size15 m-b-20">
          <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Product name" value="${product.id}">
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
      <label>Product description</label>
      <div class="bo4 of-hidden m-b-20">
        <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="Product description" readonly="true"  value="${product.description}"></textarea>
      </div>
      <hr>
      <label>Product images</label>
      <div>
          <c:forEach items="${images}" var="image">
                <img src="<c:url value="/assets/img/products/${image}"/>" height="50px" width="50px"/>
           </c:forEach>
      </div>
      <hr>
      <label style="color: red">**Note**</label>
      <div class="bo4 of-hidden m-b-20">
          <textarea style="width: 100%; border: none; padding: 20px" rows="10" placeholder="Product description"></textarea>
      </div>
      <button class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">
        Deny
      </button>
      <br/>
      <button style="background-color: red">
          Delete product
      </button>
    </div>
  </section>
</t:adminLayout>
