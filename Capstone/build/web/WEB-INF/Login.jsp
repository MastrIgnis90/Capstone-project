<%-- 
    Document   : testpage
    Created on : Feb. 22, 2021, 10:42:09 a.m.
    Author     : Sebastian Wild
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="Controller">
            Username: <input type="text" name="username"><br/>
            Password: <input type="password" name="password"><br/>
            <input type="submit" name="action" value="Login">
        </form>
        ${requestScope.message}
    </body>
</html>
