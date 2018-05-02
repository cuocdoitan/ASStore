<%-- 
    Document   : products-details
    Created on : May 1, 2018, 9:10:32 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div>
        <p style="font-weight: bold">User Id</p>
        <p>5</p>
    </div>
    <div>
        <p style="font-weight: bold">Product Id</p>
        <p>2</p>
    </div>
    <div>
        <p style="font-weight: bold">Product Name</p>
        <p>FairyTail Shoes</p>
    </div>
    <div>
        <p style="font-weight: bold">Category</p>
        <p>Shoes</p>
    </div>
    <div>
        <p style="font-weight: bold">Anime</p>
        <p>FairyTai</p>
    </div>
    <div>
        <p style="font-weight: bold">Image</p>
        <p>
            <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>"  height="100px" width="100px"/>
            <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>"  height="100px" width="100px"/>
            <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>"  height="100px" width="100px"/>
            <img src="<c:url value="/assets/img/products/kakashi_cup3.jpg"/>"  height="100px" width="100px"/>
        </p>
    </div>
    <div>
        <p style="font-weight: bold">Price</p>
        <p>55.40 $</p>
    </div>
    <div>
        <p style="font-weight: bold">Quantity</p>
        <p>49</p>
    </div>
    <div>
        <p style="font-weight: bold">Description</p>
        <textarea style="width: 200px; height: 100px">dsj
d is ds s;jsf jog wg
nlel fn ln lj adf lad nflad fn</textarea>
    </div>
        <button>
            << Back
        </button>
</div>

