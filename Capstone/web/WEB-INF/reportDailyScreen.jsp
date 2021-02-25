<%-- 
    Document   : ReportPage
    Created on : Feb. 23, 2021, 4:08:25 p.m.
    Author     : Sebastian Wild
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Controller">
            <select name="report">
                <option value="delivery">delivery</option>
                <option value="production"> production</option>
            </select><br/>
            <input type="submit" name="action" value="Wednesday 24, February 2021"><br/>
            <input type="submit" name="action" value="print">
            ${requestScope.reportDate}
            
        </form>
    </body>
</html>
