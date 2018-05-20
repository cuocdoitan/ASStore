<%-- 
    Document   : anime-list-update
    Created on : May 4, 2018, 8:27:09 PM
    Author     : leminhtung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:adminLayout>
    <form method="post" action="<c:url value='/Anime_Edit'/>" enctype="multipart/form-data">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center m-t-20 m-b-20">Update anime</h1>
                    <input type="hidden" value="${animes.id}" name="anime"/>
                    <label>Anime name:</label>
                    <div class="bo4 of-hidden size15 m-b-20">
                        <input class="sizefull s-text7 p-l-22 p-r-22" type="text" name="name" value="${animes.name}">
                    </div>
                    <label>Anime description:</label>
                    <div class="bo4 of-hidden m-b-20">
                        <textarea name="description" style="width: 100%; border: none; padding: 20px" rows="10" placeholder="anime description">${animes.description}</textarea>
                    </div>
                    <label>Anime image</label>
                    <div class="m-t-20 m-b-20">
                        <img src="<c:url value='/assets/img/anime/${animes.picture}'/>" alt="IMG-PRODUCT" width="150" height="180" />
                    </div>
                    <label>New anime image</label>
                    <div class="m-b-50">
                        <input type="file" name="image" accept="image/*"/>
                    </div>
                    <button style="width: 200px;" class="size1 bg4 bo-rad-23 s-text1 trans-0-4 m-b-20">
                        Edit
                    </button>
                </div>
            </div>
        </div>
    </form>
</t:adminLayout>
