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
        <title>Bridgeland Bread - Weekly Report Screen</title>
        <%@include file="javascript/jsReportScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>
            
        <div class="container-fluid">
                
        <div class="row">
            <%-- get report date, start and end date attributes from server --%>
            <c:set var='reportDate' value='${requestScope.reportDate}'/>
            <c:set var='startDate' value='${requestScope.startDate}'/>
            <c:set var='endDate' value='${requestScope.endDate}'/>
            
            <%-- manager side navigation menu --%>
            <jsp:include page="includes/managerSideNavigationMenu.jsp" />
            
            <%-- Weekly Production Report Section --%>
                        <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Weekly Report</p>
                    </div>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getPreviousWeeklyProductionReport('<c:out value='${startDate}'/>')"
                        >
                            <span class="material-icons">
                                keyboard_arrow_left
                            </span>
                        </button>
                        <span class="ml-2 mr-2 p-2">
                            <strong class="h5" #id="report_date">
                                <c:out value='${startDate}'/> - <c:out value="${endDate}"/>
                            </strong>
                        </span>
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getNextWeeklyProductionReport('<c:out value='${startDate}'/>')"
                        >
                            <span class="material-icons">
                                keyboard_arrow_right
                            </span>
                        </button>
                    </div>
                    <div class="col-4">
                        <div class="btn-group">                            
                            <button type="button" 
                                    class="btn btn-light"
                                    onclick="getReportDetails('<c:out value='${reportDate}'/>')"
                            >
                                Day
                            </button>
                            <button type="button" class="btn btn-secondary">Week</button>
                            <button type="button" 
                                    class="btn btn-light"
                                    onclick="getMonthlyProductionReport('<c:out value='${reportDate}'/>')"
                            >
                                Month
                            </button>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-10 d-flex justify-content-end">
                        <button type="button" 
                                class="btn btn-outline-dark pr-2 pl-2"
                                onclick="printWeeklyReportPDF('<c:out value='${reportDate}'/>')"
                        >
                            Print
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
                                <%-- 
                                    Dynamically print table of weekly report production 
                                --%>
                                <c:forEach var='reportDay' items='${requestScope.weekReportProductionList}' varStatus='i'>
                                    <td>
                                        <div class="card">
                                            <div class="card-body">
                                                <p class="card-text">${reportDay.totalOrderNumber} Orders</p>
                                                <button type="button" 
                                                        class="btn btn-outline-dark"
                                                        onclick="getReportDetails('${reportDay.reportDate}')"
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
