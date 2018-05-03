<%-- 
    Document   : category-edit
    Created on : May 3, 2018, 8:29:49 PM
    Author     : TRAN HO QUANG
--%>

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
        <!----------------------------------->
      <div class="banner bgwhite p-t-40 p-b-40">
        <div class="container">
            <div class="row">
                <jsp:include page="/admin/components/categoryItem.jsp">
                    <jsp:param name="image" value="shoe.jpg"/>
                </jsp:include>
            </div>
        </div>
    </div>
        <!------------------------>
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
        Edit Category
      </button>

    </div>
  </section>
</t:adminLayout>
