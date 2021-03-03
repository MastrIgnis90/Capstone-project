<%-- 
    Document   : reportMonthlyScreen
    Created on : Mar 1, 2021, 4:00:04 PM
    Author     : 612600
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
        <title>Bridgeland Bread - Monthly Report Screen</title>
        <%@include file="javascript/jsReportScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>
            
        <div class="container-fluid">
                
        <div class="row">
            <%-- manager side navigation menu --%>
            <jsp:include page="includes/managerSideNavigationMenu.jsp" />
            
            <%-- Monthly Production Report Section --%>
            <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Monthly Report</p>
                    </div>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <button type="button" class="btn btn-outline-dark rounded">
                            <span class="material-icons">
                                keyboard_arrow_left
                            </span>
                        </button>
                        <span class="ml-2 mr-2 p-2">
                            <strong class="h3" #id="report_date">February 2021</strong>
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
                            <button type="button" class="btn btn-light">Week</button>
                            <button type="button" class="btn btn-secondary">Month</button>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10 d-flex justify-content-end">
                        <button type="button" class="btn btn-outline-dark pr-4 pl-4 mr-2">
                            Print
                        </button>
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
                                <%-- TODO: add code to print out calendar --%>
    
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>