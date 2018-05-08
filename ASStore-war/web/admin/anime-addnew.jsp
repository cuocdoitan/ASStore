<%-- 
    Document   : anime-addnew
    Created on : May 6, 2018, 10:35:25 AM
    Author     : leminhtung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Anime_admin">
            <p>ID</p>
            <p><input type="text" name="id"/></p>
            <p>Name</p>
            <p><input type="text" name="name"/></p>           
            <p>Description</p>
            <p><input type="text" name="description"/></p>
            <p>Picture</p>
            <p>
                <input type="file" id="inputPhoto" name="picture" accept="image/*"/>
                <br/>
            </p>
            <div class="w-size25">
              <!-- Button -->
              <button class="flex-c-m size2 bg1 bo-rad-23 hov1 m-text3 trans-0-4">
                New Anime
              </button>
            </div>
        </form>
    </body>
</html>
