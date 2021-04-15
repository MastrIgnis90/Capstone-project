<%-- 
    Document   : orderDetailsScreen
    Description: ONLY FOR MANAGER USER
    Created on : Apr 13, 2021, 4:17:28 PM
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
        <title>Bridgeland Bread - Order Details</title>
        <%@include file="javascript/jsProductScreenFunction.html"%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">   
    </head>
    <body>        
        <%-- page header --%>     
        <%@include file="includes/header.html"%>

        <div class="container-fluid">
            <div class="row">
                <%-- manager side navigation menu --%>
                <jsp:include page="includes/managerSideNavigationMenu.jsp" />

                <%-- Order Details Section --%>
                <div class="col-lg-10">
                    <div class="row mt-4 ml-1">
                        <p class="h4 font-weight-light">Order Details</p>
                    </div>
                    <div class="row ml-2 mt-2">
                        <form action="customerServices" method="POST">
                            <div class="form-group">
                                <label for="orderId" class="col-form-label">Order Number</label>
                                <input type="text" class="form-control" name="orderId" id="orderId" value="${requestScope.order.orderNum}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="updateOrderBreadType" class="col-form-label">Bread Type</label>
                                <input type="text" class="form-control" name="updateOrderBreadType" id="updateOrderBreadType" value="${requestScope.order.breadType}" >
                            </div>
                            <div class="form-group">
                                <label for="updateOrderQuantity" class="col-form-label">Quantity</label>
                                <input type="number" class="form-control" name="updateOrderQuantity" id="updateOrderQuantity" min="0"
                                       step=".1" value="${requestScope.order.quantity}" required></input>
                            </div>
                            <div class="form-group">
                                <label for="updateOrderQuantitySold" class="col-form-label">Quantity Sold</label>
                                <input type="number" class="form-control" name="updateOrderQuantitySold" id="updateOrderQuantitySold" min="0"
                                       step=".1" value="${requestScope.order.quantitySold}"></input>
                            </div>
                            <div class="form-group">
                                <label for="updateOrderDeliveryDate">Delivery Date</label>
                                <input type="date" name="updateOrderDeliveryDate" id="updateOrderDeliveryDate" min="2020-01-01"
                                       value="${requestScope.order.orderDate}">
                            </div>
                            <div class="form-group">
                                <label for="updateOrderNote" class="col-form-label">Note</label>
                                <textarea type="text" class="form-control" name="updateOrderNote" id="updateOrderNote" value="${requestScope.order.notes}"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                </div>
                <%-- END Product Details Section --%>
            </div>
            <%@include file="includes/externalJSLibrary.html"%>
    </body>
</html>
