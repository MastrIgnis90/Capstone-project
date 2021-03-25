<%-- 
    Document   : reportDailyScreen
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
        <title>Bridgeland Bread - Daily Report</title>
        <%@include file="javascript/jsReportScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>
        <c:set var='reportDate' value='${requestScope.reportDate}'/>    
        
        <%@include file="includes/header.html"%>
        
        <div class="container-fluid">
        
        <div class="row">
            <%-- manager side navigation menu --%>
            <jsp:include page="includes/managerSideNavigationMenu.jsp" />
            
            <%-- Daily Production Report Section --%>
            <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Report</p>
                    </div>
                    
                    <%-- Button to get previous day production report --%>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getPreviousProductionReport('<c:out value='${reportDate}'/>')"
                        >
                            <span class="material-icons">
                                keyboard_arrow_left
                            </span>
                        </button>
                        <%-- production report date --%>
                        <span class="ml-2 mr-2 p-2">
                            <strong class="h3" #id="report_date">
                                <c:out value='${reportDate}'/>
                            </strong>
                        </span>
                        
                        <%-- Button to get the next day production report --%>
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getNextProductionReport('<c:out value='${reportDate}'/>')"
                        >
                            <span class="material-icons">
                                keyboard_arrow_right
                            </span>
                        </button>
                    </div>
                        
                    <%-- Button group to go to weekly or monthly view of production report --%>
                    <div class="col-4">
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary">Day</button>
                            <button type="button" 
                                    class="btn btn-light" 
                                    onclick="getWeeklyProductionReport('<c:out value='${reportDate}'/>')"
                            >
                                Week
                            </button>
                            <button type="button" 
                                    class="btn btn-light" 
                                    onclick="getMonthlyProductionReport('<c:out value='${reportDate}'/>')"
                            >
                                Month
                            </button>
                        </div>
                    </div>
                </div>
                
                <%-- Sort options for production report --%>
                <div class="row">
                    <div class="col-lg-8">
                        <label for="sortbyOptions">Sort by:</label>
                        <select class="selectpicker" 
                                name="sortbyOptions" 
                                id="sortbyOptions" 
                                onchange="getSortedProductionReport('<c:out value='${reportDate}'/>')"
                        >
                            <option>Order Number</option>
                            <option>Order</option>
                            <option>Note</option>
                        </select>
                        <select class="selectpicker pl-2" 
                                name="ascDesc" id="ascDesc" 
                                onchange="getSortedProductionReport('<c:out value='${reportDate}'/>')"
                        >
                            <option>Ascending</option>
                            <option>Descending</option>
                        </select>
                    </div>
                    <div class="col-lg-4">
                        <button type="button" 
                                class="btn btn-outline-dark pr-2 pl-2" 
                                onclick="printDailyReportPDF('<c:out value='${reportDate}'/>')"
                        >
                            Print
                        </button>
                    </div>
                </div>
                
                <%-- Display the day production report --%>
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
                                    Dynamically print table of daily report production 
                                --%>
                                <c:forEach var='order' items='${requestScope.dailyReportProductionList}' varStatus='i'>
                                    <tr>
                                        <th scope="row">${order.orderNum}</th>
                                        <td>${order.product}</td>
                                        <td>${order.notes}</td>
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