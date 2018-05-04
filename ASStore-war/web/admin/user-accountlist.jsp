<%-- 
    Document   : user-accountlist
    Created on : May 3, 2018, 8:26:51 PM
    Author     : leminhtung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <caption>List Of Account</caption>
                <thead>
                    
                    <tr>
                        <th>PhoneNumber </th>
                        <th>PassWord</th>
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                     <td>
                    <div>
                        <input value="01232559989">
                    </div>
                    <div>
                        <input value="0983456456">
                    </div>
                </td>
                <td>
                    <div>
                        <input value="tung123">
                    </div>
                    <div>
                        <input value="hung123">
                    </div>
                </td>
                <td>
                    <div>
                        <input value="Minh Tung">
                    </div>
                    <div>
                        <input value="viet hung">
                    </div>
                </td>
                <td>
                    <div>
                        <input value="Le">
                    </div>
                    <div>
                        <input value="Nguyen">
                    </div>
                </td>
                <td>
                    <div>
                        <input value="leminhtung@gmail.com">
                    </div>
                    <div>
                        <input value="nguyenviethung@gmail.com">
                    </div>
                </td>
                <td>
                    <div>
                        <input value="590CMT">
                    </div>
                    <div>
                        <input value="590CMT">
                    </div>
                </td>
                <td>
                    <a href="">Detail</a>
                    <a href="" onclick="return confirm('Are you sure ?')">Delete</a>
                </td>
                <td>
                    <a href="">Detail</a>
                    <a href="" onclick="return confirm('Are you sure ?')">Delete</a>
                </td>
                </tbody>
               
            </table>
        </form>
    </body>
</body>
</html>
