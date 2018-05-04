<%-- 
    Document   : anime-list
    Created on : May 4, 2018, 8:01:38 PM
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
        <form method="get" action="ListServlet">
            <table border="1" cellspacing="0" cellpadding="5">
                <caption>List Of Anime</caption>
                <thead>     
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Picture</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <td>
                        <div>
                            <input value="1">
                        </div>
                        <div>
                            <input value="2">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input value="Anime1">
                        </div>
                        <div>
                            <input value="Anime2">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input value="description1">
                        </div>
                        <div>
                            <input value="description1">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input value="pic1">
                        </div>
                        <div>
                            <input value="pic2">
                        </div>
                    </td>
                    <td>
                        <a href="">Edit</a> | 
                        <a onclick="return confirm('Are you sure ?')">Delete</a> <br>   
                        <a href="">Edit</a> | 
                        <a onclick="return confirm('Are you sure ?')">Delete</a>
                    </td> 
                </tbody> 
            </table>
        </form>
        <button onclick="window.location.href = ''">Add new</button>
    </body>
</html>
