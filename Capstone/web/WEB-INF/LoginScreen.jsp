<%-- 
    Document   : LoginScreen
    Created on : Feb 24, 2021, 4:32:41 PM
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
        <title>Bridgeland Bread - Login</title>
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
            <div class="row">
                <div class="col d-flex flex-column justify-content-center"
                    style="background-color: rgba(117, 117, 117, 0.50);">
                    <div class="row d-flex justify-content-center">
                        <p class="h4 text-light">Welcome to Bridgeland Bread</p>
                    </div>

                    <div class="row d-flex justify-content-center">
                        <form action='LoginServices' method='POST'>
                            <div class="form-group">
                                <div class="form-group row">
                                    <label for="email">
                                        <span class="material-icons md-light">email</span>
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control p-2" name="email" id="email"
                                            placeholder="Enter Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password">
                                        <span class="material-icons md-light">lock</span>
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control p-2" name="password"
                                            id="password" placeholder="Enter Password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <button type="submit" class="btn btn-primary btn-block">Log In</button>
                                </div>
                                <div class="form-group row d-flex justify-content-center mb-4">
                                    <p class="text-light">Don't have an account? </p><a class="ml-2" href="Controller?registe=true">Sign Up</a>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
     
        <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>