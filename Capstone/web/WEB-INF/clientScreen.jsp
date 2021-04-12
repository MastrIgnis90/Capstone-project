<%-- 
    Document   : clientScreen
    Description: ONLY FOR MANAGER USER
    Created on : Mar 23, 2021, 4:17:28 PM
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
        <title>Bridgeland Bread - Clients</title>
        <%@include file="javascript/jsClientScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>

        <div class="container-fluid">
            <div class="row">
                <%-- manager side navigation menu --%>
                <jsp:include page="includes/managerSideNavigationMenu.jsp" />

                <%-- Client Section --%>
                <div class="col-lg-10">
                    <div class="row mt-4 ml-1">
                        <p class="h4 font-weight-light">Manage Clients</p>
                    </div>

                    <!-- Search Bar -->
                    <div class="row">
                        <form class="form-inline ml-3 mt-4" action="customerServices" method="GET">
                            <div class="form-group">
                                <label for="searchCustomerFirstName" class="mr-2">Enter Client's First Name</label>
                                <input type="text" class="form-control" name="searchCustomerFirstName" id="searchCustomerFirstName" required>
                                <button type="submit" class="btn btn-outline-dark ml-2">Search</button>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <form class="form-inline ml-3 mt-1" action="customerServices" method="GET">
                            <div class="form-group">
                                <label for="searchCustomerLastName" class="mr-2">Enter Client's Last Name</label>
                                <input type="text" class="form-control" name="searchCustomerLastName" id="searchCustomerLastName" required>
                                <button type="submit" class="btn btn-outline-dark ml-2">Search</button>
                            </div>
                        </form>
                    </div>

                    <!-- Client Listing -->
                    <!-- sort options -->
                    <div class="row mt-4">
                        <div class="col-lg-8">
                            <label for="sortbyOptions">Sort by:</label>
                            <select class="selectpicker" 
                                    name="sortbyOptions" 
                                    id="sortbyOptions"
                                    onchange="getSortedClient()"
                                    >
                                <option>First Name</option>
                                <option>Last Name</option>
                                <option>Status</option>
                                <option>Type</option>
                                <option>Phone Number</option>
                            </select>
                            <select class="selectpicker pl-2" 
                                    name="ascDesc" 
                                    id="ascDesc"
                                    onchange="getSortedClient()"
                                    >
                                <option>Ascending</option>
                                <option>Descending</option>
                            </select>
                        </div>
                        <!-- Add New Client -->
                        <div class="col-lg-4">
                            <button type="button" class="btn btn-outline-dark pr-4 pl-4 mr-2" data-toggle="modal"
                                    data-target="#addClientModal">
                                Add New Client
                            </button>
                        </div>
                    </div>
                    <!-- Table of Clients -->
                    <div class="row">
                        <div class="col pt-4 mr-5">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">First Name</th>
                                        <th scope="col">Last Name</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Type</th>
                                        <th scope="col">Phone Number</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- 
                                        Dynamically print table of clients
                                    -->
                                    <c:forEach var='customer' items='${requestScope.customerList}' varStatus='i'>
                                        <tr>
                                            <th scope="row">${customer.firstName}</th>
                                            <td>${customer.lastName}</td>
                                            <td>${customer.status}</td>
                                            <td>${customer.customerType}</td>
                                            <td>${customer.phoneNumber}</td>
                                            <td>
                                                <button type="button" 
                                                        class="btn btn-outline-info"
                                                        onclick="editClient('<c:out value='${customer.customerId}' />')"
                                                        >
                                                    Details
                                                </button>
                                                <button type="button" 
                                                        class="btn btn-outline-danger"
                                                        onclick="deleteClient('<c:out value='${customer.customerId}' />')"
                                                        >
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Add New Client Modal -->
                <div class="modal fade" id="addClientModal" tabindex="-1" role="dialog"
                     aria-labelledby="addClientModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addClientModalLabel">Add New Client</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="customerServices" method="POST">
                                    <div class="form-group">
                                        <label for="newCustomerFirstName" class="col-form-label">First Name</label>
                                        <input type="text" class="form-control" name="newCustomerFirstName"
                                               id="newCustomerFirstName" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="newCustomerLastName" class="col-form-label">Last Name</label>
                                        <input type="text" class="form-control" name="newCustomerLastName"
                                               id="newCustomerLastName" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="newCustomerAddress" class="col-form-label">Address</label>
                                        <input type="text" class="form-control" name="newCustomerAddress"
                                               id="newCustomerAddress" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="newCustomerPostalCode" class="col-form-label">Postal Code</label>
                                        <input type="text" class="form-control" name="newCustomerPostalCode"
                                               id="newCustomerFirstName" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="newCustomerPhoneNumber" class="col-form-label">Phone Number</label>
                                        <input type="text" class="form-control" name="newCustomerPhoneNumber"
                                               id="newCustomerPhoneNumber">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Add New Client</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- END Client Section --%>
            </div>
            <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>
