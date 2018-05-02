<%-- 
    Document   : product-approving-list
    Created on : May 1, 2018, 7:52:51 PM
    Author     : Tien Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <div class="container-fluid">
        <div style="padding: 20px">
            <a href="<c:url value="/admin/products/list"/>">
                <p style="text-align: center; font-weight: bold; font-size: 15; color: #00bbec">
                    List Products
                </p>
            </a>
        </div>
        <br/>
        <br/>
        <table border="1">
            <thead>
                <tr style="text-align: center">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="ListUser">
            <tr>
                <td>1</td>
                <td>FairyTail Shoes</td>
                <td>
                    akafan ifaifandis dasidasikt jdfjdk fdjfdjrl ljawjdl hgfgdfg
                </div>
                <td>
                    <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>"  height="100px" width="100px"/>
                </td>
                <td>545.65</td>
                <td>
                    <a href="#" style="color: blue">Approve</a>
                    ||
                    <a href="#" style="color: red">Deny</a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Kakakakakakashi cup</td>
                <td>
                    jkjeej dsjldjs leo54 feklfk klqjle flakflakf\n
                    fdafjaf fdlfl fdfkdfklqf foefoae fafklaf
                </td>
                <td>
                    <img src="<c:url value="/assets/img/products/kakashi_cup.jpg"/>"  height="100px" width="100px"/>
                </td>
                <td>2343.67</td>
                <td>
                    <a href="#" style="color: blue">Approve</a>
                    ||
                    <a href="#" style="color: red">Deny</a>
                </td>
            </tr>    
            <tr>
                <td>3</td>
                <td>Cup kakakakaka</td>
                <td>
                    akafan ifaifand
                    hghgh
                    gfgfg gsgsg gsgsgssssyy www
                </td>
                <td>
                    <img src="<c:url value="/assets/img/products/mikuhatsune_bag.jpg"/>"  height="100px" width="100px"/>
                </td>
                <td>1111.76</td>
                <td>
                    <a href="#" style="color: blue">Approve</a>
                    ||
                    <a href="#" style="color: red">Deny</a>
                </td>
            </tr>
            <tr>
                <td>4</td>
                <td>Shoes Tail Fairy </td>
                <td>
                    akafan fdf fdfdfdf\n fdfdf
                </td>
                <td>
                    <img src="<c:url value="/assets/img/products/fairytail_shoe.jpg"/>"  height="100px" width="100px"/>
                </td>
                <td>545.65</td>
                <td>
                    <a href="#" style="color: blue">Approve</a>
                    ||
                    <a href="#" style="color: red">Deny</a>
                </div>
            </tr>      
        </table>

                
              
<!--            <data>
                <c:forEach items="${listProduct}" var="product">
                    <tr>
                        <td>${product.productId}</div>
                        <td>${product.productName}</div>
                        <td>
                            <img src="<c:url value="/resources/IMAGE/${product.productImage}"/>"  height="100px" width="100px"/>
                        </div>
                        <td>${product.productPrice}</div>
                        <td>${product.productDetails}</div>
                        <td>${product.manufacturerId.manufacturerName}</div>
                        <td>
                            <a href="<c:url value="/product/details?id=${product.productId}"/>">Details</a> |
                            <a href="<c:url value="/product/update?id=${product.productId}"/>">Edit</a>
                            <form action="<c:url value="/product/delete?id=${product.productId}"/>" method="post" onsubmit="return lastCheck()">
                                <input type="submit" value="Delete" style="background-color: red"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </data>-->
        </div>
    </div>
</t:layout>
