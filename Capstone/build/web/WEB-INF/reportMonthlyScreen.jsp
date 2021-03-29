<%-- 
    Document   : reportMonthlyScreen
    Created on : Mar 1, 2021, 4:00:04 PM
    Author     : Nhu Phan
--%>
<%@page import="blb.domain.products.ReportDay"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
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
            <%-- get report date in the format as Month, Year from server --%>
            <c:set var='reportMonthDate' value='${requestScope.reportMonthDate}'/>
            
            <%-- get the current date and month to pass as agrument for daily and weekly report event handlers --%>
            <%
                // get the current date in format Day Month Day Number, Year
                DateTimeFormatter fullDateFormat = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = fullDateFormat.format(now);;
                
                pageContext.setAttribute("currentDate", currentDate);
            %>
            
            <%-- manager side navigation menu --%>
            <jsp:include page="includes/managerSideNavigationMenu.jsp" />
            
            <%-- Monthly Production Report Section --%>
            <div class="col-lg-10">
                <div class="row mb-lg-4 p-lg-4 flex-wrap flex-md-nowrap">
                    <div class="col-2 mt-2">
                        <p class="h4 font-weight-light">Monthly Report</p>
                    </div>
                    <div class="col-lg-6 col-md-2 d-flex justify-content-center">
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getPreviousMonthlyProductionReport('<c:out value='${reportMonthDate}'/>')"
                        >
                            <span class="material-icons">
                                keyboard_arrow_left
                            </span>
                        </button>
                        <span class="ml-2 mr-2 p-2">
                            <strong class="h3" #id="report_date">
                                <c:out value="${reportMonthDate}"/>
                            </strong>
                        </span>
                        <button type="button" 
                                class="btn btn-outline-dark rounded"
                                onclick="getNextMonthlyProductionReport('<c:out value='${reportMonthDate}'/>')"
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
                                    onclick="getReportDetails('<c:out value='${currentDate}'/>')"
                            >
                                Day
                            </button>
                            <button type="button" 
                                    class="btn btn-light"
                                    onclick="getWeeklyProductionReport('<c:out value='${currentDate}'/>')"
                            >
                                Week
                            </button>
                            <button type="button" class="btn btn-secondary">Month</button>
                        </div>
                    </div>
                </div>
                <%-- Print button --%>
                <div class="row">
                    <div class="col-10 d-flex justify-content-end">
                        <button type="button" 
                                class="btn btn-outline-dark pr-4 pl-4 mr-2"
                                onclick="printMonthlyReportPDF('<c:out value='${reportMonthDate}'/>')"
                        >
                            Print
                        </button>
                    </div>
                </div>
                
                <%-- Calendar showing all the total number of orders for each day --%>
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
                                <%-- Print out calendar --%>
                                <%
                                    String monthReport = "";
                                    ArrayList<ReportDay> monthList = (ArrayList<ReportDay>) request.getAttribute("monthReportProductionList");
                                    
                                    for(int i = 0; i < monthList.size(); i++) {
                                        int dayNumber = i;
                                        dayNumber++;
                                        // find which day is the first day of the month and print <td></td> for the days before it
                                        if(i == 0) {
                                            switch(monthList.get(i).getReportDay()) {
                                                case "mon":
                                                    monthReport += ("<tr><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "tues":
                                                    monthReport += ("<tr><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "wed":
                                                    monthReport += ("<tr><td></td><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "thurs":
                                                    monthReport += ("<tr><td></td><td></td><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "fri":
                                                    monthReport += ("<tr><td></td><td></td><td></td><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "sat":
                                                    monthReport += ("<tr><td></td><td></td><td></td><td></td><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                    break;
                                                case "sun":
                                                    monthReport += ("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td><div class=\"card=body\"><h5 class=\"card-title\">" + i 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td></tr>");
                                                    break;
                                                default:
                                                    monthReport += "<tr><td>ERROR</td></tr>";
                                            }
                                            
                                            // got to the next index of monthList 
                                            continue;
                                        }
                                        
                                        // if it is the last day of the month
                                        if(i == monthList.size()-1) {
                                            switch(monthList.get(i).getReportDay()) {
                                                case "mon":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                                                    break;
                                                case "tues":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td><td></td><td></td><td></td><td></td></tr>");
                                                    break;
                                                case "wed":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td><td></td><td></td><td></td></tr>");
                                                    break;
                                                case "thurs":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td><td></td><td></td></tr>");
                                                    break;
                                                case "fri":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td><td></td></tr>");
                                                    break;
                                                case "sat":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td><td></td></tr>");
                                                    break;
                                                case "sun":
                                                    monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td></tr>");
                                                    break;
                                                default:
                                                    monthReport += "<tr><td>ERROR</td></tr>";
                                            }
                                            // calendar is finish, exit out of the loop
                                            break;
                                        }
                                        
                                        // print out the tags for the regular week of the month
                                        switch(monthList.get(i).getReportDay()) {
                                            case "mon":
                                                monthReport += ("<tr><td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                break;
                                            case "tues":
                                            case "wed":
                                            case "thurs":
                                            case "fri":
                                            case "sat":
                                                monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td>");
                                                break;
                                            case "sun":
                                                monthReport += ("<td><div class=\"card=body\"><h5 class=\"card-title\">" + dayNumber 
                                                                    + "</h5><p class=\"card-text\">" + monthList.get(i).getTotalOrderNumber()
                                                                    + " Orders</p><button type=\"button\" class=\"btn btn-outline-dark\" onclick=\"getReportDetails('" + monthList.get(i).getReportDate()
                                                                    + "')\">Details</button></div></div></td></tr>");
                                                break;
                                            default:
                                                monthReport += "<tr><td>ERROR</td></tr>";
                                        }     
                                    }                                
                                    // add the month report calander to the page context
                                    pageContext.setAttribute("monthReport", monthReport);
                                %>
                                <c:out value="${monthReport}" escapeXml="false"/>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="includes/externalJSLibrary.html" %>               
    </body>
</html>