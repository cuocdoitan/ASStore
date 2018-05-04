<%-- 
    Document   : anime-list-update
    Created on : May 4, 2018, 8:27:09 PM
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
        <form action="">  
            ID: <input type="text" name="phone" value="1" /><br/>  
            Name: <input type="text" name="name" value="Anime1"  /><br/>  
            Description: <input type="text" name="des" value="des1"  /><br/> 
            <div class="m-b-50">
                Picture: <input type="file" name="image" accept="image/*"/>
            </div>
        </form>
        <button onclick="window.location.href = ''">Update</button>
    </body>
</html>
