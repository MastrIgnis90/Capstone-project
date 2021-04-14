<%-- 
    Document   : clientDetailsScreen
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
        <title>Bridgeland Bread - Client Details</title>
        <%@include file="javascript/jsClientDetailsScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>

        <div class="container-fluid">
            <div class="row">
                <%-- manager side navigation menu --%>
                <jsp:include page="includes/managerSideNavigationMenu.jsp" />

                <%-- Client Details Section --%>
                <div class="col-lg-10">
                    <div class="row mt-4 ml-1">
                        <p class="h4 font-weight-light">Client Details</p>
                    </div>

                    <!-- Breadcrumbs nav -->
                    <div class="row pl-1">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb" style="background-color: white;">
                                <li class="breadcrumb-item active">Personal Information</li>
                                <li class="breadcrumb-item"><a href="#orderHistory">Order History</a></li>
                            </ol>
                        </nav>
                    </div>

                    <!-- Client Information -->
                    <div class="row ml-2" id="personalInformation">
                        <form action="customerServices" method="POST">
                            <div class="form-group">
                                <label for="updateCustomerFirstName">First Name</label>
                                <input type="text" class="form-control" name="updateCustomerFirstName" id="updateCustomerFirstName"
                                       value="${requestScope.customer.firstName}" required>
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerLastName">Last Name</label>
                                <input type="text" class="form-control" name="updateCustomerLastName" id="updateCustomerLastName" 
                                       value="${requestScope.customer.lastName}" required>
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerAddress">Address</label>
                                <input type="text" class="form-control" name="updateCustomerAddress" id="updateCustomerAddress" 
                                       value="${requestScope.customer.address}" required>
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerPostalCode">Postal Code</label>
                                <input type="text" class="form-control" name="updateCustomerPostalCode" id="updateCustomerPostalCode"
                                       value="${requestScope.customer.postalCode}" required>
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerEmail">Email</label>
                                <input type="email" class="form-control" name="updateCustomerEmail" id="updateCustomerEmail" 
                                       value="${requestScope.customer.email}" required>
                            </div>
                            <div class="form-group">
                                <label for="updateCustomerPhoneNumber">Phone number</label>
                                <input type="tel" class="form-control" name="updateCustomerPhoneNumber" id="updateCustomerPhoneNumber"
                                       value="${requestScope.customer.phoneNumber}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>

                    <!-- Order History -->
                    <div class="row mt-4" id="orderHistory">
                        <div class="col">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb" style="background-color: white;">
                                    <li class="breadcrumb-item"><a href="#personalInformation">Personal Information</a></li>
                                    <li class="breadcrumb-item active">Order History</li>
                                </ol>
                            </nav>
                        </div>
                    </div>

                    <!-- sorting and adding new order option -->
                    <div class="row ml-1" id="orderHistory">
                        <div class="col-lg-8">
                            <label for="sortbyOptions">Sort by:</label>
                            <select class="selectpicker" 
                                    name="sortbyOptions" 
                                    id="sortbyOptions"
                                    onchange="getSortedOrder()"
                                    >
                                <option>Order Number</option>
                                <option>Date</option>
                                <option>Product</option>
                                <option>Price</option>
                                <option>Standing Order</option>
                            </select>
                            <select class="selectpicker pl-2" 
                                    name="ascDesc" 
                                    id="ascDesc"
                                    onchange="getSortedOrder()"
                                    >
                                <option>Ascending</option>
                                <option>Descending</option>
                            </select>
                        </div>
                        <div class="col-lg-4">
                            <button type="button" class="btn btn-outline-dark pr-4 pl-4 mr-2" data-toggle="modal"
                                    data-target="#addOrderModal">
                                Add New Order
                            </button>
                        </div>
                    </div>

                    <!-- Table of Client Order History -->
                    <div class="row">
                        <div class="col pt-4 mr-5">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Order Number</th>
                                        <th scope="col">Date</th>
                                        <th scope="col">Product</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Standing Order</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var='order' items='${requestScope.orderList}' varStatus='i'>
                                    <tr>
                                        <th scope="row">${order.orderNum}</th>
                                        <th>${order.orderDate}</th>
                                        <td>${order.breadType}</td>
                                        <td>${order.price}</td>
                                        <td>${order.standingOrder}</td>
                                        <td>
                                            <button type="button" 
                                                    class="btn btn-outline-dark"
                                                    onclick="editOrder('<c:out value='${order.orderNum}'/>')"
                                                    >
                                                Edit
                                            </button>
                                            <button type="button" 
                                                    class="btn btn-outline-danger"
                                                    onclick="deleteOrder('<c:out value='${order.orderNum}'/>')"
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

                    <!-- Add New Order Modal -->
                    <div class="modal fade" id="addOrderModal" tabindex="-1" role="dialog"
                         aria-labelledby="addOrderModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addOrderModalLabel">Add New Order</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="customerServices" method="POST">
                                        <div class="form-group">
                                            <label for="customerId" class="col-form-label">Client ID</label>
                                            <input type="text" class="form-control" name="customerId" id="customerId" value="${requestScope.customer.customerId}" disabled>
                                        </div>
                                        <div class="form-group">
                                            <label for="newOrderBreadOptions">Sort by:</label>
                                            <select class="selectpicker" name="newOrderBreadOptions" id="newOrderBreadOptions">
                                                <option>White Sourdough</option>
                                                <option>House-Milled Einkorn</option>
                                                <option>Salt</option>
                                                <option>Butter</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="newOrderQuantity" class="col-form-label">Quantity</label>
                                            <input type="number" class="form-control" name="newOrderQuantity"
                                                   id="newOrderQuantity" min="0" step=".1" required></input>
                                        </div>
                                        <div class="form-group">
                                            <label for="newOrderDeliveryDate">Delivery Date</label>
                                            <input type="date" name="newOrderDeliveryDate" id="newOrderDeliveryDate"
                                                   min="2020-01-01">
                                        </div>
                                        <div class="form-group">
                                            <label for="newOrderNote" class="col-form-label">Note</label>
                                            <textarea type="text" class="form-control" name="newOrderNote"
                                                      id="newOrderNote"></textarea>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Add New Order</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- END Client Details Section --%>
            </div>
            <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>
