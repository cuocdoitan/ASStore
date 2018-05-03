<%-- 
    Document   : category-create.jsp
    Created on : May 2, 2018, 4:33:07 PM
    Author     : TRAN HO QUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:layout>
  <section class="bgwhite p-t-70 p-b-100">
    <div class="container">
      <h2>NEW CATEGORY</h2><br><br>
      <label>Category Name</label>
      <div class="bo4 of-hidden size15 m-b-20">
        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" placeholder="Category Name">
      </div>
      <label>Category image</label>
      <p>
        <input type="file" name="image" accept="image/*"/>
        <br/>
      </p>
      <br/>
      <br/>
      <button style="width: 200px; background: #e74c3c" class="size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4 m-r-20">
        Cancel
      </button>
      <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4">
        Add Category
      </button>

    </div>
  </section>
</t:layout>
