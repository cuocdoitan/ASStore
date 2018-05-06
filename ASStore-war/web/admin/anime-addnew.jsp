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
        <form method="post" action="InsertServlet">
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
            <p><input type="submit" value="Add new"/></p>
        </form>
    </body>
</html>
