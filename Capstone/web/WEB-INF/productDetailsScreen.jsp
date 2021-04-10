<%-- 
    Document   : productDetailsScreen
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
        <title>Bridgeland Bread - Product Details</title>
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

                <%-- Product Details Section --%>
                <div class="col-lg-10">
                    <div class="row mt-4 ml-1">
                        <p class="h4 font-weight-light">Product Details</p>
                    </div>
                    <div class="row ml-2 mt-2">
                        <form action="productServices" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="updateProductName">Product Name</label>
                                <input type="text" class="form-control" name="updateProductName" id="updateProductName" value="${requestScope.product.productName}" required/>
                            </div>
                            <div class="form-group">
                                <label for="updateProductPrice" class="col-form-label">Price</label>
                                <input type="number" class="form-control" name="updateProductPrice" id="updateProductPrice" min="0"
                                       step=".01" value="${requestScope.product.productPrice}" required />
                            </div>
                            <div class="form-group">
                                <label for="updateProductDescription" class="col-form-label">Description</label>
                                <input type="text" class="form-control" name="updateProductDescription" id="updateProductDescription" value="${requestScope.product.productDescription}" />
                            </div>
                            <div class="form-group">
                                <img class="card-img-top" src="${pageContext.request.contextPath}/${requestScope.product.productImage}" alt="product image">
                            </div>
                            <div class="form-group">
                                <label for="updateProductImage">Upload new image</label>
                                <input type="file" class="form-control-file" name="updateProductImage" id="updateProductImage">
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
