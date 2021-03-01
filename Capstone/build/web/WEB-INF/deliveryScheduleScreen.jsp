<%-- 
    Document   : deliveryScheduleScreen
    Created on : Mar 1, 2021, 4:17:23 PM
    Author     : Nhu Phan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <%@include file="includes/externalCSSLinks.html"%>
        <title>Bridgeland Bread - Delivery Schedule</title>
        <%@include file="javascript/jsReportScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%@include file="includes/header.html"%>
        
        <div class="container-fluid">
        
        <div class="row">
            <%-- manager side navigation menu --%>
            <jsp:include page="includes/managerSideNavigationMenu.jsp" />
            
            <%-- Delivery Schedule Section --%>
            <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Delivery Schedule</p>
                    </div>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <strong class="h3" #id="report_date">Thursday February 21, 2021</strong>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-8">
                        <label for="sortbyOptions">Sort by:</label>
                        <select class="selectpicker" name="sortbyOptions" id="sortbyOptions">
                            <option>Order Number</option>
                            <option>Name</option>
                            <option>Address</option>
                            <option>Delivery Time</option>
                            <option>Order</option>
                            <option>Community</option>
                        </select>
                        <select class="selectpicker pl-2" name="asc_or_desc" id="asc_or_desc">
                            <option>Ascending</option>
                            <option>Descending</option>
                        </select>
                    </div>
                    <div class="col-lg-4">
                        <button type="button" class="btn btn-outline-dark pr-2 pl-2">
                            Download
                        </button>
                    </div>
                </div>

                <div class="row">
                    <div class="col pt-4 mr-5">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Order Number</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Delivery Time</th>
                                    <th scope="col">Order</th>
                                    <th scope="col">Community</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">0001</th>
                                    <td>Jelly Bean</td>
                                    <td>100 Candy Street</td>
                                    <td>5:00 PM</td>
                                    <td>One white</td>
                                    <td>Crescent Height</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>