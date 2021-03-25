<%-- 
    Document   : deliveryScheduleScreen
    Created on : Mar 1, 2021, 4:17:23 PM
    Author     : Nhu Phan
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
        <%@include file="javascript/jsDeliveryScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>
        <!-- Get current date for delivery schedule -->
        <%
            Date date = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE MMMMM dd, yyyy");
            String dateString = dateFormat.format(date);
            
            pageContext.setAttribute("date", dateString);
        %>
        <c:set var="currentDate" value="${date}"/>
        
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
                        <strong class="h3" #id="report_date">
                            <c:out value='${currentDate}' />
                        </strong>
                    </div>
                </div>
                
                <%-- Sort options for delivery schedule --%>
                <div class="row">
                    <div class="col-lg-8">
                        <label for="sortbyOptions">Sort by:</label>
                        <select class="selectpicker" 
                                name="sortbyOptions" 
                                id="sortbyOptions"
                                onchange="getSortedDeliverySchedule('<c:out value='${currentDate}'/>')"
                        >
                            <option>Order Number</option>
                            <option>Name</option>
                            <option>Address</option>
                            <option>Delivery Time</option>
                            <option>Order</option>
                            <option>Community</option>
                        </select>
                        <select class="selectpicker pl-2" 
                                name="ascDesc" 
                                id="ascDesc"
                                onchange="getSortedDeliverySchedule('<c:out value='${currentDate}'/>')"
                        >
                            <option>Ascending</option>
                            <option>Descending</option>
                        </select>
                    </div>
                    <div class="col-lg-4">
                        <button type="button" 
                                class="btn btn-outline-dark pr-2 pl-2"
                                onclick="printDeliverySchedulePDF('<c:out value='${currentDate}'/>')"
                        >
                            Print
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
                                    <th scope="col">Note</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%--
                                    Dynamically print delivery schedule
                                --%>
                                <c:forEach var='order' items='${requestScope.deliveryScheduleList}' varStatus='i'>
                                 <tr>
                                    <th scope="row">${order.orderNum}</th>
                                    <td>${order.name}</td>
                                    <td>${order.address}</td>
                                    <td>${order.deliveryTime}</td>
                                    <td>${order.product}</td>
                                    <td>${order.community}</td>
                                    <td>${order.note}</td>
                                </tr>  
                                </c:forEach>
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