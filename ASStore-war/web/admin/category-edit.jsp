<%-- 
    Document   : category-edit
    Created on : May 3, 2018, 8:29:49 PM
    Author     : TRAN HO QUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:adminLayout>
  <section class="bgwhite p-t-70 p-b-100">
    <div class="container">
      <h2>EDIT CATEGORY</h2><br><br>
      <label>Category Name</label>
      <div class="bo4 of-hidden size15 m-b-20">
        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" value="Shoes">
      </div>
      <label>Category image</label>
      <div class="w-size13 p-t-30 m-b-30 respon5">
        <div class="wrap-slick3 flex-sb flex-w">
          <div class="slick3">
            <div class="item-slick3" data-thumb="<c:url value='/assets/img/products/kakashi_cup3.jpg'/>">
              <div class="wrap-pic-w">
                <img src="<c:url value='/assets/img/products/kakashi_cup3.jpg'/>" alt="IMG-PRODUCT" width="300" height="300" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <label>New category image</label>
      <div class="m-b-50">
        <input type="file" name="image" accept="image/*"/>
      </div>
      <button style="width: 200px; background: #e74c3c" class="size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4 m-r-20">
        Cancel
      </button>
      <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
        Edit Category
      </button>
    </div>
  </section>
</t:adminLayout>
