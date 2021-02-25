<%-- 
    Document   : reportWeeklyScreen
    Description: ONLY FOR MANAGER USER
    Created on : Feb 23, 2021, 4:17:28 PM
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
        <title>TESTING - Daily Report Screen</title>
        <%@include file="javascript/jsReportScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>
                
        <%@include file="includes/header.html"%>
            
        <div class="container-fluid">
                
        <div class="row">
            <%-- side navigation menu --%>
            <div class="col-lg-2">
                <ul class="nav navbar-light bg-light navbar-expand-lg" style="height: 100%;">
                    <div class="collapse navbar-collapse flex-column" id="sidemenu">
                        <li class="nav-item d-flex flex-row mt-lg-4">
                            <span class="material-icons md-dark p-2">
                                assignment
                            </span>
                            <a class="nav-link active" 
                               style="color: black;font-weight: bolder;"
                            >
                                Report
                            </a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                local_shipping
                            </span>
                            <a class="nav-link text-dark" 
                               href="Controller?goToDeliverySchedule=true&loginUserEmail=${sessionScope.email}"
                            >
                                Delivery Schedule
                            </a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                people
                            </span>
                            <a class="nav-link text-dark" 
                               href="Controller?goToManageClients=true&loginUserEmail=${sessionScope.email}"
                            >
                                Manage Clients
                            </a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                breakfast_dining
                            </span>
                            <a class="nav-link text-dark" 
                               href="Controller?goToManageProducts=true&loginUserEmail=${sessionScope.email}"
                            >
                                Manage Products
                            </a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                perm_identity
                            </span>
                            <a class="nav-link text-dark" 
                               href="Controller?logout=true&loginUserEmail=${sessionScope.email}"
                            >
                                Log Out
                            </a>
                        </li>
                    </div>
                </ul>
            </div>
            
            <%-- Weekly Production Report Section --%>
                        <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Report</p>
                    </div>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <button type="button" class="btn btn-outline-dark rounded">
                            <span class="material-icons">
                                keyboard_arrow_left
                            </span>
                        </button>
                        <span class="ml-2 mr-2 p-2">
                            <strong class="h4" #id="report_date">Monday Feb 21 - Sunday Feb 28, 2021</strong>
                        </span>
                        <button type="button" class="btn btn-outline-dark rounded">
                            <span class="material-icons">
                                keyboard_arrow_right
                            </span>
                        </button>
                    </div>
                    <div class="col-4">
                        <div class="btn-group">
                            <button type="button" class="btn btn-light">Day</button>
                            <button type="button" class="btn btn-secondary">Week</button>
                            <button type="button" class="btn btn-light">Month</button>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10 d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-dark pr-2 pl-2">
                            Download
                        </button>
                    </div>
                </div>

                <div class="row">
                    <div class="col pt-4 mr-5">
                        <table class="table table-bordered table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Monday</th>
                                    <th scope="col">Tuesday</th>
                                    <th scope="col">Wednesday</th>
                                    <th scope="col">Thursday</th>
                                    <th scope="col">Friday</th>
                                    <th scope="col">Saturday</th>
                                    <th scope="col">Sunday</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="card">
                                            <div class="card-body">
                                                <p class="card-text">10 Orders</p>
                                                <button type="button" class="btn btn-outline-dark">Details</button>
                                            </div>
                                        </div>
                                    </td>
                                    
                                <%-- 
                                    Dynically print table of daily report production 
                                --%>
                                <c:forEach var='order' items='${requestScope.weekReportProductionList}' varStatus='i'>
                                    <td>
                                        <div class="card">
                                            <div class="card-body">
                                                <p class="card-text">${order.totalOrderNumber} Orders</p>
                                                <button type="button" 
                                                        class="btn btn-outline-dark"
                                                        onclick="getReportDetails(${order.orderDate})"
                                                >
                                                    Details
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </c:forEach>   
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>