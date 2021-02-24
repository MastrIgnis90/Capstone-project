<%-- 
    Document   : reportScreen
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
        
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/css/bootstrap-select.min.css"
            integrity="sha512-ARJR74swou2y0Q2V9k0GbzQ/5vJ2RBSoCWokg4zkfM29Fb3vZEQyv0iWBMW/yvKgyHSR/7D64pFMmU8nYmbRkg=="
            crossorigin="anonymous" />
        
        <title>Bridgeland Bread - Daily Report</title>
        
        <c:import url="/WEB-INF/javascript/reportScreen.js"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
        
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark p-lg-8">
                <span class="navbar-brand">Bridgeland Bread</span>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#sidemenu">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </nav>
        </header>
        
        <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2">
                <ul class="nav navbar-light bg-light navbar-expand-lg" style="height: 100%;">
                    <div class="collapse navbar-collapse flex-column" id="sidemenu">
                        <li class="nav-item d-flex flex-row mt-lg-4">
                            <span class="material-icons md-dark p-2">
                                assignment
                            </span>
                            <a class="nav-link active" style="color: black;font-weight: bolder;" href="#">Report</a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                local_shipping
                            </span>
                            <a class="nav-link text-dark" href="BridgelandBreadDeliverySchedule.html">Delivery
                                Schedule</a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                people
                            </span>
                            <a class="nav-link text-dark" href="#">Manage Clients</a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                breakfast_dining
                            </span>
                            <a class="nav-link text-dark" href="#">Manage Products</a>
                        </li>
                        <li class="nav-item d-flex flex-row">
                            <span class="material-icons md-dark p-2">
                                perm_identity
                            </span>
                            <a href="#" class="nav-link text-dark">Log Out</a>
                        </li>
                    </div>
                </ul>
            </div>

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
                            <strong class="h3" #id="report_date">Thursday February 21, 2021</strong>
                        </span>
                        <button type="button" class="btn btn-outline-dark rounded">
                            <span class="material-icons">
                                keyboard_arrow_right
                            </span>
                        </button>
                    </div>
                    <div class="col-4">
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary">Day</button>
                            <button type="button" class="btn btn-light">Week</button>
                            <button type="button" class="btn btn-light">Month</button>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-8">
                        <label for="sortbyOptions">Sort by:</label>
                        <select class="selectpicker" name="sortbyOptions" id="sortbyOptions">
                            <option>Order Number</option>
                            <option>Order</option>
                            <option>Note</option>
                            <option>Delivery Time</option>
                            <option>Type</option>
                        </select>
                        <select class="selectpicker pl-2" name="asc_or_desc" id="asc_or_desc">
                            <option>Ascending</option>
                            <option>Descending</option>
                        </select>
                    </div>
                    <div class="col-lg-4">
                        <button type="button" class="btn btn-outline-dark pr-2 pl-2" onclick="printDailyReportPDF()">
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
                                    <th scope="col">Order</th>
                                    <th scope="col">Note</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%-- 
                                    Dynically print table of daily report production 
                                --%>
                                <c:forEach var='order' items='${requestScope.dailyReportProductionList}' varStatus='i'>
                                    <tr>
                                        <th scope="row">${order.orderNum}</th>
                                        <td>${order.product}</td>
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
        
    <!-- Bootstrap JS Libraries -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/js/bootstrap-select.min.js"
        integrity="sha512-yDlE7vpGDP7o2eftkCiPZ+yuUyEcaBwoJoIhdXv71KZWugFqEphIS3PU60lEkFaz8RxaVsMpSvQxMBaKVwA5xg=="
        crossorigin="anonymous"></script>
    </body>
</html>
