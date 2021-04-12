<%-- 
    Document   : 404 Error Screen
    Created on : Mar 24, 2021, 4:11:40 PM
    Author     : Nhu Phan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <%@include file="includes/externalCSSLinks.html"%>
        <title>Bridgeland Bread - 404 Page Not Found</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>

        <header>
            <nav class="navbar sticky-top navbar-dark bg-dark p-lg-8">
                <span class="navbar-brand">Bridgeland Bread</span>
            </nav>
        </header>

    <body id="LoginScreen">
        <div class="container-fluid">
            <div class="row d-flex justify-content-center mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="card" style="width:30rem;">
                        <div class="card-body">
                            <h5 class="card-title">404 Error</h5>
                            <h4 class="card-text">Page Not Found</h4>
                            <a href="Controller" class="card-link">Go Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>